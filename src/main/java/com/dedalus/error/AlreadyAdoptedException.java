package com.dedalus.error;

public class AlreadyAdoptedException extends Exception{
    public AlreadyAdoptedException (String message){
        super(message);
    }
}
