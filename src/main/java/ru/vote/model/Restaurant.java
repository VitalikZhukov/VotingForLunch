package ru.vote.model;

import java.util.Map;

public class Restaurant extends AbstractModel{

    private String name;
    private Map<String, Double> menuMap;  //no more than 10
    private int voteCount;
    private String menu;
    private String price;

    public Restaurant(Integer id, String name, Map<String, Double> menuMap, int voteCount) {
        super(id);
        this.name = name;
        this.menuMap = menuMap;
        this.voteCount = voteCount;
    }

    public Restaurant(String name, Map<String, Double> menuMap, int voteCount) {
        super(null);
        this.name = name;
        this.menuMap = menuMap;
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public Map<String, Double> getMenuMap() {
        return menuMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuMap(Map<String, Double> menuMap) {
        this.menuMap = menuMap;
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
                ", menu=" + menuMap +
                ", voteCount=" + voteCount +
                '}';
    }
}
