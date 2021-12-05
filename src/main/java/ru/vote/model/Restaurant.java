package ru.vote.model;

import java.util.Map;

public class Restaurant {
    private Integer id;
    private String name;
    private Map<String, Double> menu;  //no more than 10
    private int voteCount;

    public Restaurant(String name, Map<String, Double> menu, int voteCount) {
        this.name = name;
        this.menu = menu;
        this.voteCount = voteCount;
    }

    public boolean isNew() {
        return this.id == null;
    }






    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public Map<String, Double> getMenu() {
        return menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(Map<String, Double> menu) {
        this.menu = menu;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", menu=" + menu +
                ", voteCount=" + voteCount +
                '}';
    }
}
