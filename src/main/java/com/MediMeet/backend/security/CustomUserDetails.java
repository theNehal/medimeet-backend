package com.MediMeet.backend.security;

import com.MediMeet.backend.model.Admin;
import com.MediMeet.backend.model.Doctor;
import com.MediMeet.backend.model.Patient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private String role;

    public CustomUserDetails(Patient patient) {
        this.email = patient.getEmail();
        this.password = patient.getPassword_hash();
        this.role = "PATIENT";
    }

    public CustomUserDetails(Doctor doctor) {
        this.email = doctor.getEmail();
        this.password = doctor.getPassword_hash();
        this.role = "DOCTOR";
    }

    public CustomUserDetails(Admin admin) {
        this.email = admin.getEmail();
        this.password = admin.getPassword_hash();
        this.role = admin.getRole();   // ADMIN / RECEPTIONIST
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
