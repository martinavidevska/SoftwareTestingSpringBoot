package database.project.carrental.web.controller;
import database.project.carrental.model.Client;
import database.project.carrental.model.exceptions.InvalidArgumentsException;
import database.project.carrental.model.exceptions.InvalidUserCredentialsException;
import database.project.carrental.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        Client client = new Client();
        client.setUsername("user");

        when(authService.login(anyString(), anyString())).thenReturn(client);

        mockMvc.perform(post("/login")
                        .param("username", "user")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicles"));

        verify(authService, times(1)).login("user", "password");
    }

    @Test
    public void testLoginInvalidCredentials() throws Exception {
        when(authService.login(anyString(), anyString())).thenThrow(new InvalidUserCredentialsException());

        mockMvc.perform(post("/login")
                        .param("username", "user")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("hasError", true))
                .andExpect(model().attribute("error", "OMG I HATE THIS"));

        verify(authService, times(1)).login("user", "wrongpassword");
    }

    @Test
    public void testLoginInvalidArguments() throws Exception {
        when(authService.login(anyString(), anyString())).thenThrow(new InvalidArgumentsException());

        mockMvc.perform(post("/login")
                        .param("username", "")
                        .param("password", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("hasError", true))
                .andExpect(model().attribute("error", "OMG I HATE THIS"));

        verify(authService, times(1)).login("", "");
    }
}
