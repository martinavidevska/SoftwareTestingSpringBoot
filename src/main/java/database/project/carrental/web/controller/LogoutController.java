package database.project.carrental.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/logout")
    public class LogoutController {

        @GetMapping
        public String logout(HttpServletRequest request, Model model) {
            request.getSession().invalidate();
            return "redirect:/login";
        }
    }


