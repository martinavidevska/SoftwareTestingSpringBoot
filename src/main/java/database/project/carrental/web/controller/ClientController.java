package database.project.carrental.web.controller;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import database.project.carrental.model.exceptions.ClientNotFoundException;
import database.project.carrental.repository.ClientRepository;
import database.project.carrental.repository.RentingRepository;
import database.project.carrental.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRepository;
    private final RentingRepository rentingRepository;

    public ClientController(ClientRepository clientRepository, RentingRepository rentingRepository) {
        this.clientRepository = clientRepository;
        this.rentingRepository = rentingRepository;
    }

    @GetMapping("/info")
    public String getClientInfo(Model model, HttpServletRequest req){
        String username=req.getRemoteUser();

        Client client = this.clientRepository.findByUsername(username).orElseThrow(()->new ClientNotFoundException(username));
        List<Renting> rents=this.rentingRepository.findAllByClient(Optional.ofNullable(client));
        model.addAttribute("client", client);
        model.addAttribute("rents",rents);

        return "profile";
    }

}
