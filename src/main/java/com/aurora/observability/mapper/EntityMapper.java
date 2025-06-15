package com.aurora.observability.mapper;

public interface EntityMapper <T, R> {
    public T toEntity (R value);
}
