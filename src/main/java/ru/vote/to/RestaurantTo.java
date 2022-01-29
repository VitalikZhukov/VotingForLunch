package ru.vote.to;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    private final String name;

    @NotBlank
    private final int voteCounter;

    @ConstructorProperties({"id", "name", "voteCounter"})
    public RestaurantTo(Integer id, String name, int voteCounter) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo restaurantTo = (RestaurantTo) o;
        return voteCounter == restaurantTo.voteCounter &&
                Objects.equals(name, restaurantTo.name) &&
                Objects.equals(id, restaurantTo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, voteCounter);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", voteCounter=" + voteCounter +
                '}';
    }
}
