package com.codecool.plaza.api;

import com.codecool.plaza.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {
    private List<Shop> shops = new ArrayList<>();
    private String name;
    private String owner;
    private boolean open;
    
    public PlazaImpl(String name, String owner) {
        this.shops = shops;
        this.name = name;
        this.owner = owner;
        this.open = open;
    }
    
    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        if (isOpen()) {
            return shops;
        }
        throw new  PlazaIsClosedException("Plaza is closed!");
    }
    
    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if (!isOpen()) {
            throw new PlazaIsClosedException("Plaza is closed!");
        }
    
        if (shops.contains(shop)) {
            throw  new ShopAlreadyExistsException("Shop already exists.");
        }
        shops.add(shop);
    }
    
    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (!isOpen()){
            throw new PlazaIsClosedException("Plaza is closed!");
        }
    
        for (Shop sh : shops) {
            if (!(shop.getName().equals(sh.getName())) && !(shop.getOwner().equals(sh.getOwner()))) {
                throw  new NoSuchShopException("No such shop.");
            }
            shops.remove(shop);
        }
        
    }
    
    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen()){
            for (Shop shop : shops) {
                if (shop.getName().equals(name)) {
                    return shop;
                }
            }
            throw  new NoSuchShopException("No such shop.");
        }
    
        throw new PlazaIsClosedException("Plaza is closed!");
    }
    
    @Override
    public boolean isOpen() {
        return open;
    }
    
    @Override
    public void open() {
        this.open = true;
    }
    
    @Override
    public void close() {
        open = false;
    }
    
    public String getName() {
        return name;
    }
    
    public String getOwner() {
        return owner;
    }
    
    @Override
    public String toString() {
        return getName() + getOwner();
    }
}
