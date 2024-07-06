package database.project.carrental.web.controller;

import database.project.carrental.model.Client;
import database.project.carrental.model.ClientRentingView;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.service.ClientRentingViewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private RentingRepository rentingRepository;

    @MockBean
    private ClientRentingViewService clientRentingViewService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getClientInfo_Success() throws Exception {
        String username = "user";
        Client client = new Client();
        client.setUsername(username);
        ClientRentingView rent1 = new ClientRentingView();
        ClientRentingView rent2 = new ClientRentingView();
        List<ClientRentingView> rents = Arrays.asList(rent1, rent2);

        when(clientRepository.findByUsername(username)).thenReturn(Optional.of(client));
        when(clientRentingViewService.findByUsername(username)).thenReturn(rents);

        mockMvc.perform(get("/client/info"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(model().attribute("client", client))
                .andExpect(model().attributeExists("rents"))
                .andExpect(model().attribute("rents", rents))
                .andExpect(view().name("profile"));

        verify(clientRepository, times(1)).findByUsername(username);
        verify(clientRentingViewService, times(1)).findByUsername(username);
    }

}
