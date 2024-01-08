package database.project.carrental.web.controller;

import database.project.carrental.model.Client;
import database.project.carrental.model.ClientRentingView;
import database.project.carrental.model.exceptions.ClientNotFoundException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.service.ClientRentingViewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRepository;
    private final RentingRepository rentingRepository;
    private final ClientRentingViewService clientRentingViewService;

    public ClientController(ClientRepository clientRepository, RentingRepository rentingRepository, ClientRentingViewService clientRentingViewService) {
        this.clientRepository = clientRepository;
        this.rentingRepository = rentingRepository;
        this.clientRentingViewService = clientRentingViewService;
    }

    @GetMapping("/info")
    public String getClientInfo(Model model, HttpServletRequest req){
        String username=req.getRemoteUser();

        Client client = this.clientRepository.findByUsername(username).orElseThrow(()->new ClientNotFoundException(username));
        List<ClientRentingView> rents=this.clientRentingViewService.findByUsername(username);
        System.out.println("Rents: "  +rents);
        model.addAttribute("client", client);
        model.addAttribute("rents",rents);

        return "profile";
    }


}
