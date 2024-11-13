package com.sonotalento.event;

import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = -7686818159366076109L;
	
	private HttpServletResponse response;
	private Integer id;
	private Long idLong;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Integer id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.idLong = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Integer getId() {
		return id;
	}

	public Long getLong() {
		return idLong;
	}
}
