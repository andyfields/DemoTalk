package com.outlands.cooltalk.microservices.dto;

public class SectionDTO {

	private long id;
	private String name;
	
	public SectionDTO() {}
	
	public SectionDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SectionDTO(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
