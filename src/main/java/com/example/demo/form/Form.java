package com.example.demo.form;

import jakarta.validation.constraints.Size;

public class Form {
	@Size(min = 1, max = 10, message = "1～10文字以内にしてください!")
	private String date;
	private String naiyou;
	private int yen;
	private Long id;
	
	public Form() {}
	
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getNaiyou() {
		return naiyou;
	}	
	public void setNaiyou(String naiyou) {
		this.naiyou = naiyou;
	}
	
	public int getYen() {
		return yen;
	}

	public void setYen(int yen) {
		this.yen = yen;
	}
}