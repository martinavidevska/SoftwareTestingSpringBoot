package database.project.carrental.web.controller;


import database.project.carrental.model.Client;
import database.project.carrental.model.exceptions.InvalidArgumentsException;
import database.project.carrental.model.exceptions.InvalidUserCredentialsException;
import database.project.carrental.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping()
    public String getLoginPage() {
        return "login";
    }

    @PostMapping()
    public String login(HttpServletRequest request, Model model) {
        Client client = null;

        try {
            client = authService.login(request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error","OMG I HATE THIS");
            return "login";
        }

        request.getSession().setAttribute("user", client);
        return "redirect:/vehicles";
    }


}