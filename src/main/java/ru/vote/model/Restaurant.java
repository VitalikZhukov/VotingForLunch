package ru.vote.model;

import java.util.Map;

public class Restaurant extends AbstractModel{

    private String name;
    private int voteCount;
    private String menu;
    private String price;

    public Restaurant() {}

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
        this.voteCount = 0;
    }

    public Restaurant(String name) {
        super(null);
        this.name = name;
        this.voteCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id" + super.getId() +
                "name='" + name + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
