package com.codecool.plaza.api;

import com.codecool.plaza.exceptions.*;
import com.codecool.plaza.product.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    private String name;
    private String owner;
    private Map<Long, ShopEntryImpl> products = new HashMap<>();
    private boolean open;
    
    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.products = products;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getOwner() {
        return owner;
    }
    
    @Override
    public boolean isOpen(){
        return open;
    }
    
    @Override
    public void open(){
        open = true;
    }
    
    @Override
    public void close() {
        open = false;
    }
    
    @Override
    public List<Product> getProducts() throws ShopIsClosedException {
        List<Product> allGoods = new ArrayList<>();
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                allGoods.add(entry.getValue().getProduct());
            }
            return allGoods;
        }
        
        throw new  ShopIsClosedException("Shop is closed.");
    }
    
    @Override
    public  Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        Product productByName = null;
        
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getValue().getProduct().getName().equals(name)){
                    productByName = entry.getValue().getProduct();
                }
            }
            
            if (productByName == null){
                throw new NoSuchProductException("Invalid barcode.");
            }
            return productByName;
        }
    
        throw new  ShopIsClosedException("Shop is closed.");
    }
    
    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        float productPrice = 0;
    
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode){
                    productPrice = entry.getValue().getPrice();
                }
            }
        
            if (productPrice == 0){
                throw new NoSuchProductException("Invalid barcode.");
            }
            return productPrice;
        }
    
        throw new  ShopIsClosedException("Shop is closed.");
    }
    
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
      
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode){
                    return true;
                }
            }
            return false;
        }
        
        throw new ShopIsClosedException("Shop is closed.");
    }
    
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getValue().getProduct().getBarcode() == product.getBarcode()){
                    throw new ProductAlreadyExistsException("Product already exists!");
                }
            }
            products.put(product.getBarcode(), new ShopEntryImpl(product, quantity, price));
        }
    
        throw new ShopIsClosedException("Shop is closed.");
    }
    
    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode){
                    entry.getValue().increaseQuantity(quantity);
                }
            }
            throw new NoSuchProductException("Invalid barcode!");
        }
    
        throw new ShopIsClosedException("Shop is closed.");
    }
    
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }
    
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }
    
    @Override
    public String toString() {
        String allProducts = "";
        
        allProducts += "Clothing products: \n <<<<<<<<<<<<<<<<O>>>>>>>>>>>>>>>>";
        for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
            if (entry.getValue().getProduct() instanceof ClothingProduct){
                allProducts += "\n" + entry.getValue().getProduct().toString() + " | price (HUF): " + entry.getValue().getPrice();
            }
        }
    
        allProducts += "Food products: \n <<<<<<<<<<<<<<<<O>>>>>>>>>>>>>>>>";
        for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
            if (entry.getValue().getProduct() instanceof FoodProduct){
                allProducts += "\n" + entry.getValue().getProduct().toString() + " | price (HUF): " + entry.getValue().getPrice();
            }
        }
        
        return allProducts;
    }
    
    private class ShopEntryImpl {
        private Product product;
        private int quantity;
        private float price;
        
        ShopEntryImpl(Product product, Integer quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }
        
        Product getProduct() {
            return product;
        }
        
        int getQuantity() {
            return quantity;
        }
        
        public float getPrice() {
            return price;
        }
        
        public void setProduct(Product product) {
            this.product = product;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        
        public void setPrice(float price) {
            this.price = price;
        }
        
        public void increaseQuantity(int value) {
            this.quantity += value;
        }
        
        public void decreaseQuantity(int value) {
            this.quantity -= value;
        }
    }
}
