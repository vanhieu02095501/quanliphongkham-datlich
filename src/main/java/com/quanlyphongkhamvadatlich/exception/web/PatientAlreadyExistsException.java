package com.quanlyphongkhamvadatlich.exception.web;

import com.quanlyphongkhamvadatlich.entity.Patient;

public class PatientAlreadyExistsException extends RuntimeException{
    public PatientAlreadyExistsException(String message){super(message);}
}
