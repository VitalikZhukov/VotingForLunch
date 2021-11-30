package ru.vote.model;

import java.util.Map;

public class Restaurant {
    private String name;
    private Map<String, Double> menu;  //no more than 10
    private int voteCount;

    public Restaurant(String name, Map<String, Double> menu, int voteCount) {
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
}
