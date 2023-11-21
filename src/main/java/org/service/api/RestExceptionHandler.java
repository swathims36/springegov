package org.service.api;

import org.service.exceptions.CustomException;
import org.service.model.Error;
import org.service.model.ErrorRes;
import org.service.model.ResponseInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorRes eRes = new ErrorRes();
		Error err = new Error().code(String.valueOf(status.value())).message(status.getReasonPhrase());
		err.setMessage(ex.getMessage());
		eRes.setResponseInfo(new ResponseInfo());
		eRes.addErrorsItem(err);
		return new ResponseEntity(eRes, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorRes eRes = new ErrorRes();
		Error err = new Error().code(String.valueOf(status.value())).message(status.getReasonPhrase());
		err.setMessage(ex.getMessage());
		eRes.setResponseInfo(new ResponseInfo());
		eRes.addErrorsItem(err);
		return super.handleExceptionInternal(ex, eRes, headers, status, request);
	}

	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<Object> handleRuntimeException(CustomException ex, WebRequest request) {
		ErrorRes eRes = new ErrorRes();
		Error err = new Error();
		err.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		err.setMessage(ex.getMessage());
		ResponseInfo res = new ResponseInfo();
		eRes.setResponseInfo(res);
		eRes.addErrorsItem(err);
		return new ResponseEntity<Object>(eRes, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
