package com.example.model;


public class Poll {
	private Long id;
	private String text;
	
	public Poll(){
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long nextId) {
		this.id = nextId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
