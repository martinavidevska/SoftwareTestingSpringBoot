package database.project.carrental.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    @Test
    public void testPaymentCreation() {

        Renting renting = new Renting();
        Client client = new Client();
        Vehicle vehicle = new Vehicle();
        LocalDate paymentDate = LocalDate.now();
        Payment payment = new Payment(renting, 1500.0, paymentDate, client, vehicle, "1234567812345678", "123", "12/25", "Martina Martina");

        assertThat(payment.getRenting()).isEqualTo(renting);
        assertThat(payment.getAmount()).isEqualTo(1500.0);
        assertThat(payment.getDateOfPayment()).isEqualTo(paymentDate);
        assertThat(payment.getClient()).isEqualTo(client);
        assertThat(payment.getVehicle()).isEqualTo(vehicle);
        assertThat(payment.getCardNumber()).isEqualTo("1234567812345678");
        assertThat(payment.getCcv()).isEqualTo("123");
        assertThat(payment.getExpirationDate()).isEqualTo("12/25");
        assertThat(payment.getCardholderName()).isEqualTo("Martina Martina");
    }

    @Test
    public void testPaymentDefaultConstructor() {
        Payment payment = new Payment();
        assertThat(payment).isNotNull();
    }
}

