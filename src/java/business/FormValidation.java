/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.time.LocalDate;

/**
 *
 * @author Bill
 */
public class FormValidation {
    
    public static String validateStringInput(String fieldString, String fieldName) {
        String message = "";
        
        if (fieldString.equals("")) {
            message = fieldName + " is required.";
        }
        
        return message;
    }
    
    public static String validateIntegerInput(String fieldString, String fieldName) {
        String message = "";

        try {
            int i = Integer.parseInt(fieldString);
        }
        catch (NumberFormatException e) {
            message = fieldName + " is required and must be a valid Integer.";
        }

        return message;
    }
    
    public static String validateDateInput(String fieldString, String fieldName) {
        String message = "";

        try {
            LocalDate date = LocalDate.parse(fieldString);
        } catch (Exception e) {
            message = fieldName + " is required and must be a valid Date.";
        }

        return message;
    } 
}
