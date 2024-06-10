/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @author Admin
 * @param <T>
 */
public interface IManager<T> {

    void addAll(LinkedHashSet<Room> li);

    boolean add(T t) throws Exception;

    boolean update(T t0);

    boolean delete(T t);

    T selectedById(T t);

    ArrayList<T> selected(T t);

    ArrayList<T> sorted(Comparator c, boolean isINC);
}
