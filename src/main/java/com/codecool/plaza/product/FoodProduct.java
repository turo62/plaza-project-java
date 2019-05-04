package com.codecool.plaza.product;

import java.util.Date;

public class FoodProduct extends Product {
    private int calories;
    private Date bestBefore;
    
    public FoodProduct(long barcode, String name, String manufacturer, int calories, Date bestBefore) {
        super(barcode, name, manufacturer);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }
    
    public boolean isStillConsumable() {
        return false;
    }
    
    public int getCalories() {
        return calories;
    }
    
    public Date getBestBefore() {
        return bestBefore;
    }
    
    @Override
    public String toString() {
        return "Barcode: " + this.getBarcode() + "\n" +
            "Name: " + this.getName() + "\n" +
            "Manufacturer: " + this.getManufacturer() + "\n" +
            "Calories: " + this.getCalories() + "\n" +
            "Best before: " + this.getBestBefore();
    }
}
