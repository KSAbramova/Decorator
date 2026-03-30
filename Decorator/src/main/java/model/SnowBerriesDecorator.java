package model;

public class SnowBerriesDecorator extends DishDecorator {
    public SnowBerriesDecorator(Dish decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Снежные ягоды";
    }

    @Override
    public int getCost() {
        return super.getCost() + 5;
    }
}
