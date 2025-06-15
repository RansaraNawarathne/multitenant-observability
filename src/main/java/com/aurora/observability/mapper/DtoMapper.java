package com.aurora.observability.mapper;

public interface DtoMapper<T, R> {
    public T toDto (R value);
}
