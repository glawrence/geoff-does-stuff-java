package com.geoffdoesstuff.java.data.objects;

/**
 * An enumerator to cover the main car fuel types. There are three "categories" of power for cars, Internal Combustion
 * Engine (ICE), Battery or Battery Electric Vehicle (BEV) and Hybrid Electric Vehicle (HEV), which is sometimes PHEV
 * or Plug-in Hybrid Electric Vehicle.
 */
public enum FuelType {
    /**
     * ICE running with petrol
     */
    PETROL,
    /**
     * ICE running on diesel
     */
    DIESEL,
    /**
     * Pure electric, battery powered
     */
    BATTERY,
    /**
     * Both petrol and electric power
     */
    PETROL_HYBRID,
    /**
     * Both diesel and electric power
     */
    DIESEL_HYBRID;
}
