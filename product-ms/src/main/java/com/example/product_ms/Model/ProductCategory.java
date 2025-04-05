package com.example.product_ms.Model;

public enum ProductCategory {
    FRUIT("Fruit", "per kg"),
    VEGETABLE("Vegetable", "per bunch"),
    // Add future categories easily
    HERB("Herb", "per packet");

    private final String displayName;
    private final String pricingUnit;

    ProductCategory(String displayName, String pricingUnit) {
        this.displayName = displayName;
        this.pricingUnit = pricingUnit;
    }

    // Getters
    public String getDisplayName() { return displayName; }
    public String getPricingUnit() { return pricingUnit; }
}