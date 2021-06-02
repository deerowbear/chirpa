package com.example.chirpa.service.remote;

;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class BaseRemoteServiceController {



	/**
	 * Register any binders.
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		//TODO
		/*SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.DateFormat.ISO_DATETIME_FORMAT.formatStr());
		dateFormat.setLenient(false);
		// true passed to CustomDateEditor constructor means convert empty String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));*/
	}

	/**
	 * When a BindingResult has errors, create a string that can be thrown or logged.
	 *
	 * @param result
	 * @return
	 */
	protected String formatBindingResultErrors(BindingResult result) {
		StringBuilder sb = new StringBuilder();
		for (ObjectError objectError : result.getAllErrors()) {
			sb.append(objectError.getObjectName()).append(" : ").append(objectError.getDefaultMessage()).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Creates an ExceptionModel for eventual conversion to JSON and return to the client.
	 *
	 * @param httpStatusCode
	 * @param exceptionCode
	 * @param e
	 * @return
	 */
	protected ExceptionModel generateExceptionModel(HttpStatus httpStatusCode, ExceptionCode exceptionCode, Exception e) {
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setCode(exceptionCode.name());
		exceptionModel.setMessage(exceptionCode.getUserMessage());
		exceptionModel.setDeveloperMessage(e.getMessage());
		exceptionModel.setHttpStatusCode(httpStatusCode.value());
		return exceptionModel;
	}

	/**
	 * Generic Exception handler.
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionModel handleException(Exception e) {
		return generateExceptionModel(HttpStatus.BAD_REQUEST, ExceptionCode.GENERAL, e);
	}

	/**
	 * Exception handler for when we haven't yet implemented a method.
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
	public ExceptionModel handleUnsupportedOperationException(Exception e) {
		return generateExceptionModel(HttpStatus.NOT_IMPLEMENTED, ExceptionCode.UNSUPPORTED_OPERATION, e);
	}

}
