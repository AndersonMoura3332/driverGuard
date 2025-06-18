package com.monitor.app.DriveGuard.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document
public class Driver implements UserDetails {

    @Id
    private String id;

    private String name;

    private String licenseNumber;

    @Indexed(unique = true)
    private String email;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Sem encode aqui
    }

    // Métodos da interface UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Você pode retornar roles ou permissões aqui
    }

    @Override
    public String getUsername() {
        return email; // Utiliza o email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Altere se necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Altere se necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Altere se necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Altere se necessário
    }
}
