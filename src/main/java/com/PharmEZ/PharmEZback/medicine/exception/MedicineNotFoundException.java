package com.PharmEZ.PharmEZback.medicine.exception;

public class MedicineNotFoundException extends RuntimeException{

    public MedicineNotFoundException(String message) {
        super(message);
    }
}
