package database.project.carrental.web.controller;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import database.project.carrental.model.Vehicle;
import database.project.carrental.service.PaymentService;
import database.project.carrental.service.RentingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {
    private final PaymentService paymentService;
    private final RentingService rentingService;

    public PaymentController(PaymentService paymentService, RentingService rentingService) {
        this.paymentService = paymentService;
        this.rentingService = rentingService;
    }


        @GetMapping("/payment")
        public String showPaymentPage(@ModelAttribute("rentingId") Long rentingId, Model model) {
            Renting renting = rentingService.findById(rentingId);
            model.addAttribute("rent", renting);
            return "payment";
        }

    @PostMapping("/payment")
    public String postPayment(@RequestParam String cardNumber,
                              @RequestParam String cvv,
                              @RequestParam String cardholderName,
                              @RequestParam String expirationDate,
                              @RequestParam Long rentingId){
        double amount=this.rentingService.findById(rentingId).getTotalAmount();
        Client client=this.rentingService.findById(rentingId).getClient();
        Vehicle vehicle=this.rentingService.findById(rentingId).getVehicle();
        this.paymentService.addPayment(rentingId,amount,client,vehicle,cardNumber,cvv,expirationDate,cardholderName);
        return "rent-success";
    }
}
