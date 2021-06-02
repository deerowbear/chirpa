package com.example.chirpa.service.modelmapper;


import com.example.chirpa.constants.Constants;

public abstract class BaseModelMapper {

	protected static int toInteger(Integer integerObject) {
		if (integerObject == null) {
			return Constants.UNDEFINED_INTEGER;
		}
		return integerObject;
	}

	protected static long toLong(Long longObject) {
		if (longObject == null) {
			return Constants.UNDEFINED_INTEGER;
		}
		return longObject;
	}

	protected static double toDouble(Double doubleObject) {
		if (doubleObject == null) {
			return Constants.UNDEFINED_INTEGER;
		}
		return doubleObject;
	}

	protected static boolean toBoolean(Boolean booleanObject) {
		if (booleanObject == null) {
			return Boolean.FALSE;
		}
		return booleanObject;
	}


	protected static String toNull(String stringObject) {
		if (stringObject == null || stringObject.isEmpty()) {
			return null;
		}

		return stringObject;
	}
}
