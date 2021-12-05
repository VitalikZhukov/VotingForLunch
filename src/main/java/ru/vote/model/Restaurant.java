package ru.vote.model;

import java.util.Map;

public class Restaurant extends AbstractModel{

    private String name;
    private Map<String, Double> menu;  //no more than 10
    private int voteCount;

    public Restaurant(Integer id, String name, Map<String, Double> menu, int voteCount) {
        super(id);
        this.name = name;
        this.menu = menu;
        this.voteCount = voteCount;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id" + super.getId() +
                "name='" + name + '\'' +
                ", menu=" + menu +
                ", voteCount=" + voteCount +
                '}';
    }
}
