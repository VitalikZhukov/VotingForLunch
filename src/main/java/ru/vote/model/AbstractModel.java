package ru.vote.model;

public abstract class AbstractModel {
    public static final int START_SEQ = 10000;

    private Integer id;

    public AbstractModel() {}

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

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        AbstractModel model = (AbstractModel) obj;
        return id != null && id.equals(model.id);
    }
}
