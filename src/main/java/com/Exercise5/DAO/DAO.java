package com.Exercise5.DAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    
    T get(long id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}
