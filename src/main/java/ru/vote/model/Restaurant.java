package ru.vote.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.GET_VOTE_COUNTER, query = "SELECT r.voteCounter FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.INCREMENT_VOTE_COUNTER, query = "UPDATE Restaurant r SET r.voteCounter=:counter WHERE r.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractModel {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";
    public static final String GET_VOTE_COUNTER = "Restaurant.getVoteCounter";
    public static final String INCREMENT_VOTE_COUNTER = "Restaurant.incrementVoteCounter";

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "vote_counter")
    private Integer voteCounter;

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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id" + super.getId() +
                "name='" + name + '\'' +
                ", voteCount=" + voteCounter +
                '}';
    }
}
