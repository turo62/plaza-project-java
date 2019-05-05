package com.codecool.plaza.product;

public class ClothingProduct extends Product {
    private String material;
    private String type;
    
    public ClothingProduct(long barcode, String name, String manufacturer, String material, String type) {
        super(barcode, name, manufacturer);
        this.material = material;
        this.type = type;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public String getType() {
        return type;
    }
    public String toString() {
        return "Barcode: " + this.getBarcode() + "\n" +
            "Name: " + this.getName() + "\n" +
            "Manufacturer: " + this.getManufacturer() + "\n" +
            "Material: " + this.getMaterial() + "\n" +
            "Type: " + this.getType() + "\n";
    }
}
