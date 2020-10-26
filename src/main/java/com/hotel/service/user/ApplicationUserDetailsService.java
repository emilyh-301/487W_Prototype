package com.hotel.service.user;

import com.hotel.database.user.RolesDatabase;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Permissions;
import com.hotel.model.user.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService service;

    private final RolesDatabase roles_repo;

    public ApplicationUserDetailsService(UserService service, RolesDatabase roles_repo) {
        this.service = service;
        this.roles_repo = roles_repo;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser user = service.find(username);

        if(user == null) throw new UsernameNotFoundException("Username " + username + " not found.");

        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Roles> roles) {

        List<String> privileges = new ArrayList<>();
        List<Permissions> collection = new ArrayList<>();
        for (Roles role : roles) {
            collection.addAll(role.getRole_permissions());
        }
        for (Permissions item : collection) {
            privileges.add(item.getPermission_name());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
