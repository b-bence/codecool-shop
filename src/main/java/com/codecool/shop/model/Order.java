package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {

    private UUID userId;
    private UUID id;
    private Map<LineItem, Integer> lineItems;
    private boolean isActive = true;

    public Order() {
        this.id = UUID.randomUUID();;
        this.lineItems = new HashMap<>();
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void addlineItem(LineItem lineItem) {
        //lineItems.put(lineItem, lineItems.get(lineItem) + 1);
        lineItems.put(lineItem, 1);
    }

    public void increaseLineItemNumber(String itemName){
        for (LineItem item: lineItems.keySet()){
            if (item.getName().equals(itemName)){
                lineItems.put(item, lineItems.get(item) + 1);
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

//    public boolean checkIfItemExists(LineItem lineItem){
//        String itemName = lineItem.getName();
//        for (LineItem item: lineItems.keySet()){
//            if (item.getName().equals(itemName)){
//                return true;
//            }
//        }
//        return false;
//    }

    public void deleteOnelineItem(LineItem lineItem) {
        if (lineItems.containsKey(lineItem)) {
            lineItems.put(lineItem, lineItems.get(lineItem) - 1);
            if (lineItems.get(lineItem) <= 0) {
                lineItems.remove(lineItem);
            }
        }
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
}
