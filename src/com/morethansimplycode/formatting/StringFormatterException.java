/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.formatting;

/**
 *
 * @author Oscar
 */
public class StringFormatterException extends Exception{

    public StringFormatterException() {
    }

    public StringFormatterException(String message) {
        super(message);
    }    
    
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return "This is an Exception in StringFormatter: " + super.getMessage();
    }
    
}
