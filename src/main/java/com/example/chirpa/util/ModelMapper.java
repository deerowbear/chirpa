package com.example.chirpa.util;

public interface ModelMapper<T, U> {
	public T toModel(U domainObj) throws ModelMapperException;

	public U toDomain(T modelObj) throws ModelMapperException;
}
