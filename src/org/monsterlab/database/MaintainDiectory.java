package org.monsterlab.database;

import java.io.Serializable;

public class MaintainDiectory implements Serializable {
	private static final long serialVersionUID = 1L;
	private int _id;
	private String maintain_num;
	private String maintain_name;

	public MaintainDiectory() {
		super();
	}

	public MaintainDiectory(int _id, String maintain_num, String maintain_nae) {
		super();
		this._id = _id;
		this.maintain_num = maintain_num;
		this.maintain_name = maintain_nae;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getMaintain_num() {
		return maintain_num;
	}

	public void setMaintain_num(String maintain_num) {
		this.maintain_num = maintain_num;
	}

	public String getMaintain_name() {
		return maintain_name;
	}

	public void setMaintain_nae(String maintain_name) {
		this.maintain_name = maintain_name;
	}

}
