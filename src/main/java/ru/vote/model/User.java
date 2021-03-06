package ru.vote.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.login, u.email"),
        @NamedQuery(name = User.SET_RESTAURANT_ID, query = "UPDATE User u SET u.restaurantId=:restaurantId WHERE u.id=:id"),
        @NamedQuery(name = User.RESET_ALL_RESTAURANT_ID, query = "UPDATE User u SET u.restaurantId=:restaurantId"),
        @NamedQuery(name = User.GET_RESTAURANT_ID, query = "SELECT u.restaurantId FROM User u WHERE u.id=:id")
})
@Entity
@Table(name = "users")
public class User extends AbstractModel {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";
    public static final String SET_RESTAURANT_ID = "User.setRestaurantId";
    public static final String RESET_ALL_RESTAURANT_ID = "User.resetAllRestaurantId";
    public static final String GET_RESTAURANT_ID = "User.gerRestaurantId";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    @JoinColumn(name = "user_id") //https://stackoverflow.com/a/62848296/548473
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Transient
    private LocalDateTime checkTimeVote;

    public User() {}

    //copy constructor
    public User(User u) {
        this(u.id, u.login, u.email, u.password, u.registered, u.restaurantId, u.checkTimeVote, u.enabled, u.roles);
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
