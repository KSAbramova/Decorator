package model;

public class DoubleVenisonDecorator extends DishDecorator {
    public DoubleVenisonDecorator(Dish decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Двойная порция оленнины";
    }

    @Override
    public int getCost() {
        return super.getCost() + 20;
    }
}
