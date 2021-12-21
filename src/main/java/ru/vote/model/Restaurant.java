package ru.vote.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.voteCounter DESC"),
        @NamedQuery(name = Restaurant.GET_VOTE_COUNTER, query = "SELECT r.voteCounter FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.INCREMENT_VOTE_COUNTER, query = "UPDATE Restaurant r SET r.voteCounter=:counter WHERE r.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractModel{

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";
    public static final String GET_VOTE_COUNTER = "Restaurant.getVoteCounter";
    public static final String INCREMENT_VOTE_COUNTER = "Restaurant.incrementVoteCounter";

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "vote_counter", nullable = false)
    @NotBlank
    private Integer voteCounter;

    @Transient
    private String menu;

    @Transient
    private String price;

    public Restaurant() {}

    //copy constructor
    public Restaurant(Restaurant r) {
        this(r.name, r.voteCounter);
    }

    public Restaurant(String name) {
        super(null);
        this.name = name;
        this.voteCounter = 0;
    }

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
        this.voteCounter = 0;
    }

    public Restaurant(String name, Integer voteCounter) {
        this.name = name;
        this.voteCounter = voteCounter;
    }

    public Restaurant(Integer id, String name, Integer voteCounter) {
        super(id);
        this.name = name;
        this.voteCounter = voteCounter;
    }

    public String getName() {
        return name;
    }

    public int getVoteCounter() {
        return voteCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVoteCounter(Integer voteCounter) {
        this.voteCounter = voteCounter;
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
                ", voteCount=" + voteCounter +
                '}';
    }
}
