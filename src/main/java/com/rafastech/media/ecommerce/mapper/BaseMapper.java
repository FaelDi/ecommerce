package com.rafastech.media.ecommerce.mapper;

import java.util.List;

public interface BaseMapper<D,E> {
    List<D> parseListDTO(List<E> objects);
    List<E> parseListEntity(List<D> objectsDTO);
    D parseDTO(E object);
    E parseEntity(D objectDTO);
}
