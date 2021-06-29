package com.budan.springappblog.security.model;

import com.budan.springappblog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class JwtUserDetails implements UserDetails {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final boolean isEnabled;
    private final Set<GrantedAuthority> authorities;

    public JwtUserDetails(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.isEnabled = user.getIsEnabled();
        this.authorities = new HashSet<>();
        this.authorities.add(new SimpleGrantedAuthority(user.getRoles()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.firstName;
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
        return isEnabled;
    }
}
