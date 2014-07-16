package org.monsterlab.database;

import java.io.Serializable;

public class FaultDiectory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int _id = 0;
	private String fault_num;
	private String fault_name;

	public FaultDiectory() {
		super();
	}

	public FaultDiectory(int _id, String fault_num, String fault_name) {
		super();
		this._id = _id;
		this.fault_num = fault_num;
		this.fault_name = fault_name;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getFault_num() {
		return fault_num;
	}

	public void setFault_num(String fault_num) {
		this.fault_num = fault_num;
	}

	public String getFault_name() {
		return fault_name;
	}

	public void setFault_name(String fault_name) {
		this.fault_name = fault_name;
	}

}
