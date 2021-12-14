package ru.vote.model;

public class Menu extends AbstractModel{
    private int restaurantId;
    private String dish;
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
