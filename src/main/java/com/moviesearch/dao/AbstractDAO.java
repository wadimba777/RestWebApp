package com.moviesearch.dao;

import java.util.List;

public abstract class AbstractDAO <T> {
    public abstract void add(T t);
    public abstract List<T> getAll();
    public abstract T getById(int id);
    public abstract boolean removeById(int id);
}
