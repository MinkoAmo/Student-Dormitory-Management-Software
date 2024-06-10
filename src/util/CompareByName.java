/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;
import models.Student;

/**
 *
 * @author Admin
 */
public class CompareByName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        String name1 = CompareByName.getToSortName(o1.getName());
        String name2 = CompareByName.getToSortName(o2.getName());
        return name1.compareTo(name2);
    }

    private static String getToSortName(String name) {
        name = name.trim();
        name = name.toLowerCase();
        int index = 0;
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i) == ' '){
                index = i;
            }
        }
        return name.substring(index).trim()+" "+name.substring(0, index);
    }
}
