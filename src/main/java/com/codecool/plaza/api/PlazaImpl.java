package com.codecool.plaza.api;

import com.codecool.plaza.exceptions.*;

import java.util.List;

public class PlazaImpl implements Plaza {
    private List<Shop> shops;
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
        if (isOpen()){
            for (Shop sh : shops) {
                if (sh.getName().equals(shop.getName())) {
                    throw  new ShopAlreadyExistsException("Shop already exists.");
                }
            }
            shops.add(shop);
        }
        
        throw new PlazaIsClosedException("Plaza is closed!");
    }
    
    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen()){
            for (Shop sh : shops) {
                if (sh.getName().equals(shop.getName()) && sh.getOwner().equals(shop.getOwner())) {
                    shops.remove(shop);
                }
            }
            throw  new NoSuchShopException("No such shop.");
        }
    
        throw new PlazaIsClosedException("Plaza is closed!");
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
        open = true;
    }
    
    @Override
    public void close() {
        open = false;
    }
    
    public String getName() {
        return name;
    }
}
