package org.monsterlab.database;

import java.io.Serializable;

public class MaintainItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private int _id = 0;
	private String maintain_diectory_name;
	private String maintain_item_num;
	private String maintain_item_name;
	private String maintain_item_content;

	public MaintainItem() {
		super();
	}

	public MaintainItem(int _id, String maintain_diectory_name,
			String maintain_item_num, String maintain_item_name,
			String maintain_item_content) {
		super();
		this._id = _id;
		this.maintain_diectory_name = maintain_diectory_name;
		this.maintain_item_num = maintain_item_num;
		this.maintain_item_name = maintain_item_name;
		this.maintain_item_content = maintain_item_content;
	}

	public String getMaintain_diectory_name() {
		return maintain_diectory_name;
	}

	public void setMaintain_diectory_name(String maintain_diectory_name) {
		this.maintain_diectory_name = maintain_diectory_name;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getMaintain_item_num() {
		return maintain_item_num;
	}

	public void setMaintain_item_num(String maintain_item_num) {
		this.maintain_item_num = maintain_item_num;
	}

	public String getMaintain_item_name() {
		return maintain_item_name;
	}

	public void setMaintain_item_name(String maintain_item_name) {
		this.maintain_item_name = maintain_item_name;
	}

	public String getMaintain_item_content() {
		return maintain_item_content;
	}

	public void setMaintain_item_content(String maintain_item_content) {
		this.maintain_item_content = maintain_item_content;
	}

}
