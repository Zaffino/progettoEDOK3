package com.unitn.zaffino.dbConnection;

import java.util.LinkedList;

public interface DBdao<T> {


    public LinkedList<T> selectAll();

    public boolean insert(T x);

    public boolean remove(int x);

    public LinkedList<T> selectByID(int id);

}
