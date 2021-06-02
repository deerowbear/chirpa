package com.example.chirpa.util;


import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class ModelUtils {

	//Keeps track of model mapper instances so we don't have to create new objects every time
	private static Map<Class<?>, ModelMapper<?, ?>> instances = new HashMap<Class<?>, ModelMapper<?, ?>>();

	/**frr
	 * Creates a model object from the given domain object using a specific mapper.
	 * @param domainObj
	 * @param mapperClass
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U, V extends U, W extends ModelMapper<T,U>> T toModel(V domainObj, Class<W> mapperClass)
			throws ModelMapperException {
		if(domainObj == null) {
			throw new IllegalArgumentException("Domain object cannot be null");
		}

		//Create an instance of the object mapper
		W mapperInstance = createModelMapper(mapperClass);
		if(mapperInstance == null) {
			throw new ModelMapperException("Model mapper instance does not exist");
		}

		return mapperInstance.toModel(domainObj);
	}

	/**
	 * Creates a list of model objects from the given domain collection using a specific mapper.
	 * @param domainObjs
	 * @param mapperClass
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U, V extends U, W extends ModelMapper<T,U>> List<T> toModels(
			Collection<V> domainObjs, Class<W> mapperClass) throws ModelMapperException {
		if(domainObjs == null) {
			throw new IllegalArgumentException("Domain object collection cannot be null");
		}

		List<T> models = new ArrayList<T>();
		for(U domain : domainObjs) {
			models.add(toModel(domain, mapperClass));
		}

		return models;
	}

	/**
	 * Uses a generic copyProperties method to copy properties from the domain to the
	 * model object.
	 * @param domainObj
	 * @param modelObj
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U> T toModel(U domainObj, T modelObj) throws ModelMapperException {
		genericCopyProperties(modelObj, domainObj);

		return modelObj;
	}

	/**
	 * Creates a domain object from the given model object using a specific mapper.
	 * @param modelObj
	 * @param mapperClass
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U, V extends T, W extends ModelMapper<T,U>> U toDomain(
			V modelObj, Class<W> mapperClass) throws ModelMapperException {
		if(modelObj == null) {
			throw new IllegalArgumentException("Model object cannot be null");
		}

		//Create an instance of the object mapper
		W mapperInstance = createModelMapper(mapperClass);
		if(mapperInstance == null) {
			throw new ModelMapperException("Model mapper instance does not exist");
		}

		return mapperInstance.toDomain(modelObj);
	}

	/**
	 * Creates a list of domain objects from the given model collection using a specific mapper.
	 * @param modelObjs
	 * @param mapperClass
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U, V extends T, W extends ModelMapper<T,U>> List<U> toDomains(
			Collection<V> modelObjs, Class<W> mapperClass) throws ModelMapperException {
		if(modelObjs == null) {
			throw new IllegalArgumentException("Model object collection cannot be null");
		}

		List<U> domains = new ArrayList<U>();
		for(T model : modelObjs) {
			domains.add(toDomain(model, mapperClass));
		}

		return domains;
	}

	/**
	 * Creates a Set of domain objects from the given model collection using a specific mapper.
	 * This is the same as the toDomains method except that the List has been converted to a Set.
	 * 
	 * @param modelObjs
	 * @param mapperClass
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U, V extends T, W extends ModelMapper<T,U>> Set<U> toDomainSet(
			Collection<V> modelObjs, Class<W> mapperClass) throws ModelMapperException {

		List<U> models = toDomains(modelObjs, mapperClass);
		return new HashSet<>(models);
	}

	/**
	 * Uses a generic copyProperties method to copy properties from the model to the
	 * domain object.
	 * @param modelObj
	 * @param domainObj
	 * @return
	 * @throws ModelMapperException
	 */
	public static <T, U> U toDomain(T modelObj, U domainObj) throws ModelMapperException {
		genericCopyProperties(domainObj, modelObj);

		return domainObj;
	}

	/**
	 * Uses Apache Commons BeanUtils to copy properties from the second object to the first.
	 * @param destinationObj
	 * @param origObj
	 * @throws ModelMapperException
	 */
	public static <T, U> void genericCopyProperties(T destinationObj, U origObj) throws ModelMapperException {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(destinationObj, origObj);

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ModelMapperException(e);
		}
	}

	/**
	 * Creates a new instance of an object mapper, given its class type
	 * @param mapperClass which implements ModelMapper<T,U>
	 * @return
	 * @throws ModelMapperException on error creating a new instance of the mapper
	 */
	@SuppressWarnings("unchecked")
	private static <T, U, V extends ModelMapper<T,U>> V createModelMapper(Class<V> mapperClass)
			throws ModelMapperException {
		if(mapperClass == null) {
			throw new IllegalArgumentException("Object mapper class cannot be null");
		}

		//Get an instance of the model mapper, either from our instance map or by creating one
		V mapperInstance = null;
		try {
			if(!instances.containsKey(mapperClass)) {
				mapperInstance = mapperClass.newInstance(); //Call the default constructor
				instances.put(mapperClass, mapperInstance);
			}
			else {
				mapperInstance = (V) instances.get(mapperClass);
			}

			return mapperInstance;

		} catch (Exception e) {
			throw new ModelMapperException("Could not create instance of model mapper", e);
		}
	}
}
