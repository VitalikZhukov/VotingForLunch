package ru.vote.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.ALL_SORTED, query = "SELECT m FROM Menu m ORDER BY m.restaurantId"),
        @NamedQuery(name = Menu.GET_LIST_BY_RESTAURANT_ID, query = "SELECT m FROM Menu m WHERE m.restaurantId=:restaurantId"),
        @NamedQuery(name = Menu.DELETE_ALL_BY_RESTAURANT_ID, query = "DELETE FROM Menu m WHERE m.restaurantId=:restaurantId")
})
@Entity
@Table(name = "menu")
public class Menu extends AbstractModel {

    public static final String DELETE = "Menu.delete";
    public static final String ALL_SORTED = "Menu.getAllSorted";
    public static final String GET_LIST_BY_RESTAURANT_ID = "Menu.getListByRestaurantId";
    public static final String DELETE_ALL_BY_RESTAURANT_ID = "Menu.deleteAllByRestaurantId";

    @Column(name = "restaurant_id", nullable = false)
    @NotBlank
    private int restaurantId;

    @Column(name = "dish", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String dish;

    @Column(name = "price", nullable = false)
    @NotBlank
    private double price;

    public Menu() {}

    //copy constructor
    public Menu(Menu m) {
        this(m.restaurantId, m.dish, m.price);
    }

    public Menu(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Menu(int restaurantId, String dish, double price) {
        this.restaurantId = restaurantId;
        this.dish = dish;
        this.price = price;
    }

    public Menu(Integer id, int restaurantId, String dish, double price) {
        super(id);
        this.restaurantId = restaurantId;
        this.dish = dish;
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
