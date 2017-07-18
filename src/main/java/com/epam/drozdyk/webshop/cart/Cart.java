package com.epam.drozdyk.webshop.cart;

import com.epam.drozdyk.webshop.bean.model.Instrument;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private HashMap<Instrument, Integer> cart;

    public Cart() {
        this.cart = new LinkedHashMap<>();
    }

    public HashMap<Instrument, Integer> getCart() {
        return cart;
    }

    public int getCartSize() {
        return cart.size();
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Object cartItem : cart.entrySet()) {
            Map.Entry entry = (Map.Entry) cartItem;
            Instrument instrument = (Instrument) entry.getKey();
            Integer quantity = (Integer) entry.getValue();
            totalCost += instrument.getPrice() * quantity;
        }

        return totalCost;
    }

    public void addItem(Instrument key) {
        cart.put(key, 1);
    }

    public void increaseAmount(Instrument key) {
        Integer quantity = cart.get(key);
        cart.replace(key, ++quantity);
    }

    public void clearCart() {
        cart.clear();
    }

    public void reduceAmount(Instrument key) {
        Integer quantity = cart.get(key);
        cart.replace(key, --quantity);
    }

    public void removeItem(Instrument key) {
        cart.remove(key);
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public Integer getQuantity(Instrument key) {
        return cart.get(key);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cart", cart)
                .toString();
    }
}
