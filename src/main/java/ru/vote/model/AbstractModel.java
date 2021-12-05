package ru.vote.model;

public abstract class AbstractModel {
    private Integer id;

    public AbstractModel(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
