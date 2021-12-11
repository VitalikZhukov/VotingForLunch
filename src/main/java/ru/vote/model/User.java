package ru.vote.model;

import java.time.LocalDateTime;
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
    private Restaurant choose;
    private LocalDateTime checkTimeVote;

    public User() {}

    public User(Integer id, String login, String email, String password, Date registered, Restaurant restaurant, LocalDateTime checkTimeVote, Role role, Role... roles) {
        super(id);
        this.login = login;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.choose = restaurant;
        this.checkTimeVote = checkTimeVote;
        this.roles = EnumSet.of(role, roles);
        this.enabled = true;
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Restaurant getChoose() {
        return choose;
    }

    public void setChoose(Restaurant choose) {
        this.choose = choose;
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
                ", choose=" + choose +
                ", checkTimeVote=" + checkTimeVote +
                '}';
    }
}
