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
        this.id = new UUID(10, 1);
        this.lineItems = new HashMap<>();
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void addlineItem(LineItem lineItem) {
        if (lineItems.containsKey(lineItem)) {
            lineItems.put(lineItem, lineItems.get(lineItem) + 1);
        }
        lineItems.put(lineItem, 1);
    }

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

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }
}
