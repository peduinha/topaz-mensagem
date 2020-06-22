package com.mensagem.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;


/**
 * Container for every response made by the middleware.
 */
public class HttpResponseDTO {

	private Boolean success;

	private String  message;

	private Map<String, Object> content;

	private HttpStatus status;

	public Boolean isSuccess() {
		return this.success;
	}

	
	public void setSuccess(final Boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * Get the map representing the content.
	 *
	 * @return {@link Map} with the content.
	 */
	public Map<String, Object> getContent() {

		if (Objects.isNull(this.content)) {
			this.content = new HashMap<>();
		}

		return this.content;
	}

	/**
	 * Get the value of the specified key.
	 *
	 * @param key
	 *            {@link String} key to be searched in the map.
	 * @return {@link Object} for the key.
	 */
	public Object getContent(final String key) {
		if (!Objects.isNull(this.getContent())) {
			return this.getContent().get(key);
		} else {
			return null;
		}

	}

	/**
	 * Adds the object as the specified key.
	 *
	 * @param key
	 *            {@link String} containing a key for the map.
	 * @param value
	 *            {@link Object} being the value for the key.
	 */
	public void addContent(final String key, final Object value) {
		if (null != value) {
			this.getContent().put(key, value);
		}
	}

	/**
	 * Adds the content using the {@link Object} class name as key.
	 *
	 * @param value
	 *            {@link Object} to be part of content.
	 */
	public void addContent(final Object value) {
		if (null != value) {
			final String key = value.getClass().getSimpleName();
			this.getContent().put(key, value);
		}
	}

	/**
	 * @param status
	 *            {@link #status}
	 */
	public void setStatus(final HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return {@link #status}
	 */
	public HttpStatus getStatus() {
		/*if (null != this.status) {
			return HttpStatus.OK;
		}*/
		return this.status;
	}

	
	public static HttpResponseDTO failStri(String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	
	public static HttpResponseDTO fail(final String code, String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	public static HttpResponseDTO fail(final String code, final Object content) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.addContent(content);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	public static HttpResponseDTO fail(final String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	/**
	 * Fail response with message by code and message
	 *
	 * @param code
	 *            message code
	 * @param message
	 *            message text
	 * @param status
	 * @return fail response
	 */
	public static HttpResponseDTO fail(final String code, final String message, final HttpStatus status) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(status);
		return response;
	}

	/**
	 * Success response without content.
	 *
	 * @return a success response.
	 */
	public static HttpResponseDTO success() {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	/**
	 * Success response with message.
	 *
	 * @param code
	 *            message code
	 * @param message
	 *            message text
	 * @return fail response
	 */
	public static HttpResponseDTO success(final String message) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setMessage(message);
		response.setSuccess(Boolean.TRUE);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	/**
	 * Success response with single content.
	 *
	 * @param key
	 *            content key
	 * @param content
	 *            content value
	 * @return Success response with single content.
	 */
	public static HttpResponseDTO success(final String message, final String key, final Object content) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.setMessage(message);
		response.addContent(key, content);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	/**
	 * Success response with single content.
	 *
	 * @param key
	 *            content key
	 * @param content
	 *            content value
	 * @param status
	 * @return Success response with single content.
	 */
	public static HttpResponseDTO success(final String key, final Object content, final HttpStatus status) {
		final HttpResponseDTO response = new HttpResponseDTO();
		response.setSuccess(Boolean.TRUE);
		response.addContent(key, content);
		response.setStatus(status);
		return response;
	}
}