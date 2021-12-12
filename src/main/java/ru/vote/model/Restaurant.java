package ru.vote.model;

public class Restaurant extends AbstractModel{

    private String name;
    private Integer voteCounter;
    private String menu;
    private String price;

    public Restaurant() {}

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
        this.voteCounter = 0;
    }

    public Restaurant(String name) {
        super(null);
        this.name = name;
        this.voteCounter = 0;
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
