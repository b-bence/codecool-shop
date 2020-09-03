package com.codecool.shop.model;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {

    private UUID userId;
    private UUID id;
    private Map<LineItem, Integer> lineItems;
    private boolean isActive = true;

    private Map<String, String> orderDetails;

    public Order() {
        this.id = UUID.randomUUID();;
        this.lineItems = new HashMap<>();
        this.orderDetails = new HashMap<>();
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void addlineItem(LineItem lineItem) {
        lineItems.put(lineItem, 1);
    }

    public boolean hasLineItem(LineItem lineItem) {
        boolean hasLineItem = false;
        for (LineItem li: lineItems.keySet()) {
            if (lineItem.equals(li)) {
                hasLineItem = true;
            }
        }
        return hasLineItem;
    }

    public LineItem getLineItemById(int productId) {
        LineItem lineItem = null;
        for (LineItem item: lineItems.keySet()) {
            if (item.getId() == productId) {
                lineItem = item;
            }
        }
        return lineItem;
    }

    public void addOrderDetail(String field, String detail){
        orderDetails.put(field, detail);
    }

    public void increaseLineItemNumber(String itemName){
        for (LineItem item: lineItems.keySet()){
            if (item.getName().equals(itemName)){
                lineItems.put(item, lineItems.get(item) + 1);
            }
        }
    }

    public void increaseLineItemNumber(int productId){
        for (LineItem item: lineItems.keySet()){
            if (item.getId() == productId){
                lineItems.put(item, lineItems.get(item) + 1);
            }
        }
    }

    public void decreaseLineItemNumber(int productId){
        for (LineItem item: lineItems.keySet()){
            if (item.getId() == productId){
                lineItems.put(item, lineItems.get(item) - 1);
            }
        }
    }

    public boolean checkIfLineItemExists(String itemName){
        for (LineItem item: lineItems.keySet()){
            if (item.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }

    public void deleteOneTypeOflineItem(LineItem lineItem) {
        if (lineItems.containsKey(lineItem)) {
            lineItems.remove(lineItem);
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public Map<LineItem, Integer> getLineItems() {
        return lineItems;
    }

    public float getTotalPriceForCart() {
        float total = 0;
        for (var entry: lineItems.entrySet()) {
            float price = entry.getKey().getDefaultPrice();
            int pieceOfProduct = entry.getValue();
            total += price * pieceOfProduct;
        }
        return total;
    }

    public String getTotalPriceWithCurrencyAsString() {
        Currency lineitemCurrency = null;
        for (LineItem lineItem: lineItems.keySet()) {
            lineitemCurrency = lineItem.getDefaultCurrency();
            break;
        }
        String currency = lineitemCurrency == null ? "" : lineitemCurrency.toString();
        return getTotalPriceForCart() + " " + currency;
    }

    public int getLineItemQuantity(LineItem lineItem) {
        int quantity = 0;
        for (LineItem li: lineItems.keySet()) {
            if (li.equals(lineItem)) {
                quantity = lineItems.get(li);
            }
        }
        return quantity;
    }

    public int getAllLineItemCount() {
        int count = 0;
        for (Integer quantity: lineItems.values()) {
            count += quantity;
        }
        return count;
    }
}
