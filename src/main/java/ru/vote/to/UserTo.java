package ru.vote.to;

public class UserTo {
    private Integer id;

    private String login;

    private String email;

    private String password;

    private Integer restaurantId;

    public UserTo() {
    }

    public UserTo(Integer id, String login, String email, String password, Integer restaurantId) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.restaurantId = restaurantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
