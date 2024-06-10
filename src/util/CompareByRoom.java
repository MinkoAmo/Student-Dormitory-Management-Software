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
 * @author Nguyen Nam lam
 */
public class CompareByRoom implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getRoom().compareTo(o2.getRoom());
    }

}
