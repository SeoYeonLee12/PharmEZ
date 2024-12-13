package com.PharmEZ.PharmEZback.pharmacy.exception;

public class NearbyPharmacyNotFoundException extends RuntimeException{
    public NearbyPharmacyNotFoundException(String message) {
        super(message);
    }
}
