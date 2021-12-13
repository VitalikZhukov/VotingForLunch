package ru.vote.model;

import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractModel{

    private String email;
    private String login;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> roles;
    private Integer restaurantId;
    private LocalDateTime checkTimeVote;

    public User() {}

    //copy constructor
    public User(User u) {
        this(u.restaurantId, u.login, u.email, u.password, u.registered, u.restaurantId, u.checkTimeVote, u.enabled, u.roles);
    }

    public User(Integer id, String login, String email, String password, Integer restaurantId, Role role, Role... roles) {
        this(id, login, email, password, new Date(), restaurantId, LocalDateTime.now(), true, EnumSet.of(role, roles));
    }

    public User(Integer id, String login, String email, String password, Date registered, Integer restaurantId, LocalDateTime checkTimeVote, boolean enabled, Collection<Role> roles) {
        super(id);
        this.login = login;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.restaurantId = restaurantId;
        this.checkTimeVote = checkTimeVote;
        this.enabled = enabled;
        setRoles(roles);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getCheckTimeVote() {
        return checkTimeVote;
    }

    public void setCheckTimeVote(LocalDateTime checkTimeVote) {
        this.checkTimeVote = checkTimeVote;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", choose=" + restaurantId +
                ", checkTimeVote=" + checkTimeVote +
                '}';
    }
}
