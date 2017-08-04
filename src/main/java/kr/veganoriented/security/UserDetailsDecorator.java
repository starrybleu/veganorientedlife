package kr.veganoriented.security;

import kr.veganoriented.domain.User;
import kr.veganoriented.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by terrylee on 17. 8. 1.
 */
public class UserDetailsDecorator implements UserDetails {

    public static final String ROLES_PREFIX = "ROLE_";

    private User user;

    public UserDetailsDecorator(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();

        if(roles == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(new List[]{roles}).map(
                role -> (GrantedAuthority) () -> ROLES_PREFIX + role
        ).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() { return user.getEmailAddress(); }

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
