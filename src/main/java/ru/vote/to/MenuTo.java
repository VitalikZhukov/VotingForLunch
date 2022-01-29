package ru.vote.to;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.util.Objects;

public class MenuTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    private final String dish;

    @NotBlank
    private final double price;

    @ConstructorProperties({"id", "dish", "price"})
    public MenuTo(Integer id, String dish, double price) {
        super(id);
        this.dish = dish;
        this.price = price;
    }

    public String getDish() {
        return dish;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuTo menuTo = (MenuTo) o;
        return Double.compare(menuTo.price, price) == 0 &&
                Objects.equals(dish, menuTo.dish) &&
                Objects.equals(id, menuTo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dish, price);
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", dish='" + dish + '\'' +
                ", price=" + price +
                '}';
    }
}
