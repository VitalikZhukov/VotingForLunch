package ru.vote.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractModel{

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
