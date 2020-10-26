package com.hotel.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "application_user")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 250)
    protected Long user_id;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    protected Set<Roles> user_roles;

    public ApplicationUser() {
        this.user_roles = new HashSet<>();
    }

    public ApplicationUser(Long user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.user_roles = new HashSet<>();
    }

    public ApplicationUser(Long user_id, String username, String password, Set<Roles> user_roles) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.user_roles = user_roles;
    }

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.user_roles = new HashSet<>();
    }

    public ApplicationUser(String username, String password, Set<Roles> user_roles) {
        this.username = username;
        this.password = password;
        this.user_roles = user_roles;
    }

    //<editor-fold desc="Getters and Setters">
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long userID) {
        this.user_id = userID;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Set<Roles> getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(Set<Roles> user_roles) {
        this.user_roles = user_roles;
    }

    public void setUser_roles(Roles ... user_roles) {
        this.user_roles.addAll(Arrays.asList(user_roles));
    }

    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");

        authorities.add(authority);
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>
}
