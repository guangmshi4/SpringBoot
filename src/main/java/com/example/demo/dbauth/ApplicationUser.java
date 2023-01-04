package com.example.demo.dbauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ApplicationUser implements UserDetails {


    private final List<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
//    private final boolean isAccountNonExpired;
//    private final boolean isAccountNonLocked;
//    private final boolean isCredentialsNonExpired;
//
//    private final boolean isEnabled;

    public ApplicationUser(UserRole user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.grantedAuthorities = Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
