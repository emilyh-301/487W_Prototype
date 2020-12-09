package com.hotel.model.user;

import com.hotel.model.item.Cart;
import com.hotel.model.room.Room;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "application_user")
//@ControllerAdvice
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 250)
    private Long user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Roles> user_roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cart> user_carts;

    @OneToOne
    @JoinColumn(name = "room")
    private Room room;

    public ApplicationUser() {
        this.user_roles = new HashSet<>();
    }

    public ApplicationUser(Long user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.user_roles = new HashSet<>();
        this.user_carts = new HashSet<>();
    }

    public ApplicationUser(Long user_id, String username, String password, Set<Roles> user_roles, Set<Cart> user_carts) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.user_roles = user_roles;
        this.user_carts = user_carts;
    }

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.user_roles = new HashSet<>();
        this.user_carts = new HashSet<>();
    }

    public ApplicationUser(String username, String password, Set<Roles> user_roles, Set<Cart> user_carts) {
        this.username = username;
        this.password = password;
        this.user_roles = user_roles;
        this.user_carts = user_carts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationUser)) return false;
        ApplicationUser that = (ApplicationUser) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username);
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
        return getGrantedAuthorities(getPrivileges(user_roles));
    }

    private List<String> getPrivileges(Collection<Roles> roles) {

        List<String> privileges = new ArrayList<>();
        List<Permissions> collection = new ArrayList<>();
        for (Roles role : roles) {
            privileges.add(role.getRole_name());
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
            //System.out.println(privilege);
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Cart> getUser_carts() {
        return user_carts;
    }

    public void setUser_carts(Set<Cart> user_carts) {
        this.user_carts = user_carts;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    //</editor-fold>

    public boolean hasActiveCart() {
        for(Cart c : user_carts) {
            if(!c.isCompleted()) return true;
        }

        return false;
    }

    public Cart getActiveCart() {
        for(Cart c : user_carts) {
            if(!c.isCompleted()) return c;
        }

        return null;
    }

//    @ModelAttribute("cartSize")
//    public int getCartSize(){
//        for(Cart c: user_carts)
//        {
//            if(c != null && !c.isCompleted()) return c.getItems().size();
//        }
//
//        return 0;
//    }
}
