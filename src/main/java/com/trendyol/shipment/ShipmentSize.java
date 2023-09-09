package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize incrementSize() {
        ShipmentSize[] values = ShipmentSize.values();
        int currentIndex = this.ordinal();
        int nextIndex = currentIndex + 1;

        return (nextIndex < values.length) ? values[nextIndex] : values[values.length - 1];

    }

    public boolean isGreaterThan(ShipmentSize otherSize) {
        return this.ordinal() > otherSize.ordinal();
    }

    public static ShipmentSize getSmallestSize() {
        return values()[0];
    }
}
