package com.rafastech.media.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T> {

    List<T> findAll();

    Optional<T> findById(UUID id);

    T create(T obj);

    T edit(UUID id, T obj);

    void delete(UUID id);

}