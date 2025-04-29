package com.poistenie.app.data.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entity representing a user in the system, implementing Spring Security's UserDetails interface.
 */
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin; // true = ADMIN, false = USER

    /**
     * Returns the authorities granted to the user (based on admin flag).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + (admin ? "ADMIN" : "USER"));
        return List.of(authority);
    }

    /**
     * Checks if the user is the owner of the given email.
     */
    public boolean isOwnerOf(String email) {
        return this.email.equalsIgnoreCase(email);
    }

    /**
     * Checks if the user can modify data related to the given email (either admin or owner).
     */
    public boolean canModify(String email) {
        return this.isAdmin() || isOwnerOf(email);
    }

    // UserDetails interface methods (simplified - all returning true)

    /**
     * Indicates whether the user's account has expired. (Always true)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. (Always true)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired. (Always true)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. (Always true)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the username used for authentication (email).
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Returns the password used for authentication.
     */
    @Override
    public String getPassword() {
        return password;
    }

    // Getters and Setters

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
