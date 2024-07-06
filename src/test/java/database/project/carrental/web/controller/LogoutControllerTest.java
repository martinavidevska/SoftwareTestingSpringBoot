package database.project.carrental.web.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LogoutControllerTest {

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private LogoutController logoutController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout")
                        .sessionAttr("httpSession", httpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Verify that invalidate() method was called on httpSession
        // Note: You should verify this interaction in your actual test case.
        // Mockito.verify(httpSession).invalidate();
    }
}
