package database.project.carrental.model;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {

    @Test
    public void testClientCreationWithAllFields() {
        // Arrange
        Role role = Role.USER;
        Client client = new Client("martina_videvska", "password123", "Martina Videvska", "martina.videvska@gmail.com", "073333333", "D12345678", "NY 5", Role.USER);

        // Act & Assert
        assertThat(client.getUsername()).isEqualTo("martina_videvska");
        assertThat(client.getPassword()).isEqualTo("password123");
        assertThat(client.getName()).isEqualTo("Martina Videvska");
        assertThat(client.getEmail()).isEqualTo("martina.videvska@gmail.com");
        assertThat(client.getPhoneNumber()).isEqualTo("073333333");
        assertThat(client.getDriverLicenseNumber()).isEqualTo("D12345678");
        assertThat(client.getAddress()).isEqualTo("NY 5");
        assertThat(client.getRole()).isEqualTo(role);
    }


    @Test
    public void testGetAuthorities() {
        Client client = new Client("martina_videvska", "password123", "Martina Videvska", "martina.videvska@gmail.com", "073333333", "D12345678", "NY 5", Role.USER);

        Collection<? extends GrantedAuthority> authorities = client.getAuthorities();

        assertThat(authorities).hasSize(1);
        assertThat(authorities.iterator().next()).isInstanceOf(SimpleGrantedAuthority.class);
        assertThat(authorities.iterator().next().getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    public void testUserDetailsMethods() {
        Client client = new Client("martina_videvska", "password123", "Martina Videvska", "martina.videvska@gmail.com", "073333333", "D12345678", "NY 5", Role.USER);

        assertThat(client.isAccountNonExpired()).isTrue();
        assertThat(client.isAccountNonLocked()).isTrue();
        assertThat(client.isCredentialsNonExpired()).isTrue();
        assertThat(client.isEnabled()).isTrue();
    }
}

