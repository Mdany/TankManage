package org.monsterlab.database;

import java.io.Serializable;

public class FaultItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private int _id = 0;
	private String fault_diectory_name;
	private String fault_item_num;
	private String fault_item_name;
	private String fault_item_first;
	private String fault_item_second;
	private String fault_item_third;

	public FaultItem() {
		super();

	}

	public FaultItem(int _id, String fault_diectory_name,
			String fault_item_num, String fault_item_name,
			String fault_item_first, String fault_item_second,
			String fault_item_third) {
		super();
		this._id = _id;
		this.fault_diectory_name = fault_diectory_name;
		this.fault_item_num = fault_item_num;
		this.fault_item_name = fault_item_name;
		this.fault_item_first = fault_item_first;
		this.fault_item_second = fault_item_second;
		this.fault_item_third = fault_item_third;
	}

	public String getFault_diectory_name() {
		return fault_diectory_name;
	}

	public void setFault_diectory_name(String fault_diectory_name) {
		this.fault_diectory_name = fault_diectory_name;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getFault_item_num() {
		return fault_item_num;
	}

	public void setFault_item_num(String fault_item_num) {
		this.fault_item_num = fault_item_num;
	}

	public String getFault_item_name() {
		return fault_item_name;
	}

	public void setFault_item_name(String fault_item_name) {
		this.fault_item_name = fault_item_name;
	}

	public String getFault_item_first() {
		return fault_item_first;
	}

	public void setFault_item_first(String fault_item_first) {
		this.fault_item_first = fault_item_first;
	}

	public String getFault_item_second() {
		return fault_item_second;
	}

	public void setFault_item_second(String fault_item_second) {
		this.fault_item_second = fault_item_second;
	}

	public String getFault_item_third() {
		return fault_item_third;
	}

	public void setFault_item_third(String fault_item_third) {
		this.fault_item_third = fault_item_third;
	}

}
