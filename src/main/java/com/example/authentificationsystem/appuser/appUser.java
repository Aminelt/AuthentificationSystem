package com.example.authentificationsystem.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class appUser implements UserDetails {
    @SequenceGenerator(
        name =  "user_sequence",
        sequenceName = "user_sequence",
        allocationSize =  1
)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "user_sequence"
)
    private Long id ;
    private String FirstName;
    private String LastName;
    private String email ;
    private String password ;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole ;
    private boolean locked ;
    private boolean enabled ;

    public appUser(String FirstName, String LastName, String email, String password, AppUserRole appUserRole) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
       // this.locked = locked;
       // this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name()) ;
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getLastName() {
        return LastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
