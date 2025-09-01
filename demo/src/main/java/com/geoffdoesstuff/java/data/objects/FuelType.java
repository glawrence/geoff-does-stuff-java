package com.geoffdoesstuff.java.data.objects;

import java.util.Set;

/**
 * An enumerator to cover the main car fuel types. There are three "categories" of power for cars, Internal Combustion
 * Engine (ICE), Battery or Battery Electric Vehicle (BEV) and Hybrid Electric Vehicle (HEV), which is sometimes PHEV
 * or Plug-in Hybrid Electric Vehicle. Some manufacturers use mild-hybrid, which is where an electric motor and battery
 * are used but can only be changed by the ICE and discharged when the car decides.
 *
 * This enum shows how you can have code within the enum, they can be more than just a simple type.
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
     * Both petrol and electric power, plug-in hybrid
     */
    PETROL_HYBRID,
    /**
     * Both petrol and electric power, but "mild" electric power
     */
    PETROL_MILD_HYBRID,
    /**
     * Both diesel and electric power, plug-in hybrid
     */
    DIESEL_HYBRID;

    /**
     * Set of petrol related FuelType
     */
    public static final Set<FuelType> USES_PETROL = Set.of(PETROL, PETROL_HYBRID, PETROL_MILD_HYBRID);
    /**
     * Set of diesel related FuelType
     */
    public static final Set<FuelType> USES_DIESEL = Set.of(DIESEL, DIESEL_HYBRID);
    /**
     * Set of electric related FuelType
     */
    public static final Set<FuelType> USES_ELECTRIC = Set.of(BATTERY, DIESEL_HYBRID, PETROL_HYBRID, PETROL_MILD_HYBRID);
    /**
     * Set of battery only related FuelType
     */
    public static final Set<FuelType> USES_PURE_ELECTRIC = Set.of(BATTERY);
}
