package com.example.demo.entity;

public class EntForm {
	private int id;
	private String date;
	private String naiyou;
	private int yen;

	public EntForm() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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