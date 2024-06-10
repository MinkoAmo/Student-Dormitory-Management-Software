/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Admin
 */
public class StudentsSizeException extends Exception{

    public StudentsSizeException() {
    }

    public StudentsSizeException(String message) {
        super(message);
    }

    public StudentsSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
