package ar.com.magm.ti.app.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.magm.ti.service.SimpleResponse;
/**
 * 
 * @author magm
 *
 */
@ControllerAdvice
@PropertySource({ "classpath:/config/security.properties",
		"classpath:/config/security-${spring.profiles.active:local}.properties" })
public class RestResponseEntityEsceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private Environment env;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			WebRequest request) {
		return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, status, request);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
		return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {

		return handleExceptionInternal(ex, message(HttpStatus.FORBIDDEN, ex), new HttpHeaders(), HttpStatus.FORBIDDEN,
				request);
	}

	private SimpleResponse message(final HttpStatus httpStatus, final Exception ex) {
		if (!Boolean.parseBoolean(env.getProperty("exception.verbose", "false"))) {
			final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
			final String devMessage = ExceptionUtils.getRootCauseMessage(ex);
			return new SimpleResponse(httpStatus.value(), message, devMessage);
		} else {
			return new SimpleResponse(httpStatus.value(), ex);
		}

	}
}
