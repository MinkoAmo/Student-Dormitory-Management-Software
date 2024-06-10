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
public class NameException extends Exception {

    public NameException(String message) {
        super(message);
    }

    public static void checkFormatName(String str) throws NameException {

        // Danh sách các kí tự dặc biệt
        char[] chs = {'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '=', '_', '+', '[', ']',
            '{', '}', '|', '\'', '\\', ';', ':', '"', '<', '>', '?', '/', ',', '.'};
        
        // Danh sách các kí tự số
        char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // Kí tự đặc biệt.
        for (char ch : num) {
            if(str.indexOf(ch) != -1){
                throw new NameException("Tên không được chứa các kí tự số!");
            }
        }
        // Kí tự đặc biệt.
        for (char ch : chs) {
            if(str.indexOf(ch) != -1){
                throw new NameException("Tên không được chứa các kí tự đặc biệt!");
            }
        }
    }
}
