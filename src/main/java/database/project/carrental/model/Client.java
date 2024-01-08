package database.project.carrental.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name="client")
public class Client implements UserDetails {
    @Id
    private String username;

    private String password;
    @Column(name="name_client")
    private String name;
    @Column(name="email_client")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="driver_license_number")
    private String driverLicenseNumber;
    private String address;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;


    public Client(String username, String password, String name,String email, String phoneNumber, String driverLicenseNumber, String address, Role role) {
        this.username = username;
        this.password=password;
        this.name=name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.address = address;
        this.role=role;
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
        this.name="";
        this.email = "";
        this.phoneNumber = "";
        this.driverLicenseNumber = "";
        this.address = "";
        this.role = Role.ADMIN;
    }

    public Client() {

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
