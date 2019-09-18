package com.example.barivara.api;

public class House {
	private String bariwalar_nam;
	private int bariwalar_reg_id;
	private String zilla;
	private String upazilla;
	private String elaka;
	private boolean family_basha;
	private int shobar_ghorer_shongkhya;
	private int bathroomer_shongkhya;
	private boolean khabar_ghor;
	private boolean rannaghor;
	private int barivara;

	public House() {}

	public House(String bariwalar_nam, int bariwalar_reg_id, String zilla, String upazilla, String elaka, boolean family_basha, int shobar_ghorer_shongkhya, int bathroomer_shongkhya, boolean khabar_ghor, boolean rannaghor, int barivara) {
		this.bariwalar_nam = bariwalar_nam;
		this.bariwalar_reg_id = bariwalar_reg_id;
		this.zilla = zilla;
		this.upazilla = upazilla;
		this.elaka = elaka;
		this.family_basha = family_basha;
		this.shobar_ghorer_shongkhya = shobar_ghorer_shongkhya;
		this.bathroomer_shongkhya = bathroomer_shongkhya;
		this.khabar_ghor = khabar_ghor;
		this.rannaghor = rannaghor;
		this.barivara = barivara;
	}

	public String getBariwalar_nam() {
		return bariwalar_nam;
	}

	public int getBariwalar_reg_id() {
		return bariwalar_reg_id;
	}

	public String getZilla() {
		return zilla;
	}

	public String getUpazilla() {
		return upazilla;
	}

	public String getElaka() {
		return elaka;
	}

	public boolean isFamily_basha() {
		return family_basha;
	}

	public int getShobar_ghorer_shongkhya() {
		return shobar_ghorer_shongkhya;
	}

	public int getBathroomer_shongkhya() {
		return bathroomer_shongkhya;
	}

	public boolean isKhabar_ghor() {
		return khabar_ghor;
	}

	public boolean isRannaghor() {
		return rannaghor;
	}

	public int getBarivara() {
		return barivara;
	}

	public void setBariwalar_nam(String bariwalar_nam) {
		this.bariwalar_nam = bariwalar_nam;
	}

	public void setBariwalar_reg_id(int bariwalar_reg_id) {
		this.bariwalar_reg_id = bariwalar_reg_id;
	}

	public void setZilla(String zilla) {
		this.zilla = zilla;
	}

	public void setUpazilla(String upazilla) {
		this.upazilla = upazilla;
	}

	public void setElaka(String elaka) {
		this.elaka = elaka;
	}

	public void setFamily_basha(boolean family_basha) {
		this.family_basha = family_basha;
	}

	public void setShobar_ghorer_shongkhya(int shobar_ghorer_shongkhya) {
		this.shobar_ghorer_shongkhya = shobar_ghorer_shongkhya;
	}

	public void setBathroomer_shongkhya(int bathroomer_shongkhya) {
		this.bathroomer_shongkhya = bathroomer_shongkhya;
	}

	public void setKhabar_ghor(boolean khabar_ghor) {
		this.khabar_ghor = khabar_ghor;
	}

	public void setRannaghor(boolean rannaghor) {
		this.rannaghor = rannaghor;
	}

	public void setBarivara(int barivara) {
		this.barivara = barivara;
	}

	@Override
	public String toString() {
		return "House{" +
				"bariwalar_nam='" + bariwalar_nam + '\'' +
				", bariwalar_reg_id=" + bariwalar_reg_id +
				", zilla='" + zilla + '\'' +
				", upazilla='" + upazilla + '\'' +
				", elaka='" + elaka + '\'' +
				", family_basha=" + family_basha +
				", shobar_ghorer_shongkhya=" + shobar_ghorer_shongkhya +
				", bathroomer_shongkhya=" + bathroomer_shongkhya +
				", khabar_ghor=" + khabar_ghor +
				", rannaghor=" + rannaghor +
				", barivara=" + barivara +
				'}';
	}
}
