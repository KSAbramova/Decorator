package model;

public class FierySauceDecorator extends DishDecorator {
    public FierySauceDecorator(Dish decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Огненный соус";
    }

    @Override
    public int getCost() {
        return super.getCost() + 10;
    }
}
