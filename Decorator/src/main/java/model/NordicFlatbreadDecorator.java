package model;

public class NordicFlatbreadDecorator extends DishDecorator {
    public NordicFlatbreadDecorator(Dish decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Нордская лепешка";
    }

    @Override
    public int getCost() {
        return super.getCost() + 7;
    }
}
