package com.springboot3.restapis.interfaces;

import com.springboot3.restapis.dto.Response;

import java.util.List;

/**
 * Generic CRUD interface which returns Response wrappers so callers get payload + status.
 *
 * @param <T>  entity/DTO type
 * @param <ID> id type
 */
public interface CurdInterface<T, ID> {

    Response<T> create(T entity);

    Response<T> update(ID id, T entity);

    Response<T> findById(ID id);

    Response<List<T>> findAll();

    Response<Void> deleteById(ID id);

    Response<Boolean> existsById(ID id);
}
