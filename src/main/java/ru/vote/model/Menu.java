package ru.vote.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu extends AbstractModel{

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
