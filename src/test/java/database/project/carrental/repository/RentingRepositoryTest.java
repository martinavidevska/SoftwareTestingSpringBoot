package database.project.carrental.repository;

import database.project.carrental.model.Client;
import database.project.carrental.model.Renting;
import database.project.carrental.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class RentingRepositoryTest {

    @Autowired
    private RentingRepository rentingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testFindAllByClient() {
        Client client = new Client("martina", "password", "Martina", "martina@gmail.com", "1234567890", "SK5555AA", "Skopje", Role.USER);
        clientRepository.save(client);

        Renting renting1 = new Renting(LocalDate.now(), LocalDate.now().plusDays(5), 100.0, null, client, null, null);
        Renting renting2 = new Renting(LocalDate.now(), LocalDate.now().plusDays(7), 150.0, null, client, null, null);
        rentingRepository.save(renting1);
        rentingRepository.save(renting2);

        List<Renting> rentings = rentingRepository.findAllByClient(Optional.of(client));

        assertEquals(2, rentings.size());
        assertEquals(client.getUsername(), rentings.get(0).getClient().getUsername());
        assertEquals(client.getUsername(), rentings.get(1).getClient().getUsername());
    }

    @Test
    public void testFindAllByClientEmptyResult() {
        Client client = new Client("martina", "password", "Martina", "martina@gmail.com", "1234567890", "SK5555AA", "Skopje", Role.USER);
        clientRepository.save(client);

        List<Renting> rentings = rentingRepository.findAllByClient(Optional.empty());

        assertEquals(0, rentings.size());
    }
}
