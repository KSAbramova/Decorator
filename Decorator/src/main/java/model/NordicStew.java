package model;

public class NordicStew implements Dish {
    @Override
    public String getDescription() {
        return "Нордское рагу";
    }

    @Override
    public int getCost() {
        return 50;
    }
}
