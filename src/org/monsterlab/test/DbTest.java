package org.monsterlab.test;

import org.monsterlab.database.FaultDiectoryDbManager;
import org.monsterlab.database.FaultItemDbManager;
import org.monsterlab.database.MaintainDiectoryDbManager;
import org.monsterlab.database.MaintainItemDbManager;

public class DbTest {
	public static void main(String[] args){
		FaultDiectoryDbManager fd_db = new FaultDiectoryDbManager();
		FaultItemDbManager fi_db=new FaultItemDbManager();
		MaintainDiectoryDbManager md_db=new MaintainDiectoryDbManager();
		MaintainItemDbManager mi_db=new MaintainItemDbManager();
		
		for(int i=0;i<50;i++){
			fd_db.insertData(new Integer(i).toString(), "fault_diectory:"+i);
			fi_db.insertData(new Integer(i).toString(),"fault_diectory_name:"+i, "fault_item_name:"+i, "fault_item_first"+i, "fault_item_second"+i, "fault_item_third"+i);
			md_db.insertData(new Integer(i).toString(), "maintain_name:"+i);
			mi_db.insertData(new Integer(i).toString(), "maintain_diectory_name:"+i,"maintain_item_name:"+i);
		}
		
	}

}
