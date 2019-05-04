package com.codecool.plaza.product;

public abstract class Product {
    protected long barcode;
    protected String name;
    protected String manufacturer;
    
    public Product(long barcode, String name, String manufacturer) {
        this.barcode = barcode;
        this.name = name;
        this.manufacturer = manufacturer;
    }
    
    public long getBarcode() {
        return barcode;
    }
    
    public String getName() {
        return name;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public String  toString() {
        return "Product barcode: " + this.getBarcode() + "\n"
                            + "Product name: " + this.getName() + "\n"
                            + "Manufacturer: " + this.getManufacturer();
    }
}
