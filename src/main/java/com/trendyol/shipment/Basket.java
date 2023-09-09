package com.trendyol.shipment;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private List<Product> products;
    private static final int BASKET_SHIPMENT_SIZE_THRESHOLD = 3;
    private final EnumMap<ShipmentSize, Integer> productSizeCounts = new EnumMap<>(ShipmentSize.class);

    private void initializeProductSizeCounts() {
        for (Product product : products) {
            ShipmentSize currentProductSize = product.getSize();
            int newCount = productSizeCounts.getOrDefault(currentProductSize, 0) + 1;
            productSizeCounts.put(currentProductSize, newCount);
        }
    }

    private ShipmentSize findLargestProductSize()
    {
        ShipmentSize largest = ShipmentSize.getSmallestSize();
        for (Product product : products) {
            if(product.isGreaterThan(largest))
            {
                largest = product.getSize();
            }
        }
        return largest;
    }
    public ShipmentSize getShipmentSize() {
        initializeProductSizeCounts();

        ShipmentSize determinedPackageSize = findLargestProductSize();

        for (Map.Entry<ShipmentSize, Integer> entry : productSizeCounts.entrySet()) {

            int count = entry.getValue();
            ShipmentSize size = entry.getKey();

            if(count >= BASKET_SHIPMENT_SIZE_THRESHOLD)
            {
                determinedPackageSize = size.incrementSize();
            }
        }

        return determinedPackageSize;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
