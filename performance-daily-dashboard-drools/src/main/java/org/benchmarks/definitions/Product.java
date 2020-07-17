package org.benchmarks.definitions;

public enum Product {
    DROOLS("DROOLS"),
    OPTAPLANNER("OPTAPLANNER");

    private String product;

    Product(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
