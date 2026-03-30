package model;

public abstract class DishDecorator implements Dish {
    protected final Dish decoratedDish;

    public DishDecorator(Dish decoratedDish) {
        this.decoratedDish = decoratedDish;
    }

    @Override
    public String getDescription() {
        return decoratedDish.getDescription();
    }

    @Override
    public int getCost() {
        return decoratedDish.getCost();
    }
}
