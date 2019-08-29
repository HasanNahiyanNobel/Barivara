package com.example.barivara.api;

public class Home {
	private String dhoron;
	private Integer songkha_shobar_ghor;
	private Integer songkha_bathroom;
	private Boolean khabar_ghor;
	private Boolean ranna_ghor;

	public Home(String dhoron, Integer songkha_shobar_ghor, Integer songkha_bathroom, Boolean khabar_ghor, Boolean ranna_ghor) {
		this.dhoron = dhoron;
		this.songkha_shobar_ghor = songkha_shobar_ghor;
		this.songkha_bathroom = songkha_bathroom;
		this.khabar_ghor = khabar_ghor;
		this.ranna_ghor = ranna_ghor;
	}

	public String getDhoron() {
		return dhoron;
	}

	public Integer getSongkha_shobar_ghor() {
		return songkha_shobar_ghor;
	}

	public Integer getSongkha_bathroom() {
		return songkha_bathroom;
	}

	public Boolean getKhabar_ghor() {
		return khabar_ghor;
	}

	public void setDhoron(String dhoron) {
		this.dhoron = dhoron;
	}

	public void setSongkha_shobar_ghor(Integer songkha_shobar_ghor) {
		this.songkha_shobar_ghor = songkha_shobar_ghor;
	}

	public void setSongkha_bathroom(Integer songkha_bathroom) {
		this.songkha_bathroom = songkha_bathroom;
	}

	public void setKhabar_ghor(Boolean khabar_ghor) {
		this.khabar_ghor = khabar_ghor;
	}

	public void setRanna_ghor(Boolean ranna_ghor) {
		this.ranna_ghor = ranna_ghor;
	}

	public Boolean getRanna_ghor() {
		return ranna_ghor;
	}

	@Override
	public String toString() {
		return "Home{" +
				"dhoron='" + dhoron + '\'' +
				", songkha_shobar_ghor=" + songkha_shobar_ghor +
				", songkha_bathroom=" + songkha_bathroom +
				", khabar_ghor=" + khabar_ghor +
				", ranna_ghor=" + ranna_ghor +
				'}';
	}
}
