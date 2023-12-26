package database.project.carrental.web.controller;

import database.project.carrental.model.exceptions.InvalidArgumentsException;
import database.project.carrental.service.AuthService;
import database.project.carrental.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final ClientService clientService;

    public RegisterController(AuthService authService, ClientService clientService) {
        this.authService = authService;

        this.clientService = clientService;
    }

    @GetMapping()
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "register";
    }

    @PostMapping()
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String phoneNumber,
                           @RequestParam String driverLicenseNumber,
                           @RequestParam String address){
        try{
            System.out.println("OKS");
            this.clientService.register(username,password,email,phoneNumber,driverLicenseNumber,address);
            return "redirect:/login";
        } catch (InvalidArgumentsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}