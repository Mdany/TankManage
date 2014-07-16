package org.monsterlab.database;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库管理类，对数据库进行插入、删除、查询操作
 * 
 */
public class FaultItemDbManager {

	private DbHelper dbHelper = null;
	private SQLiteDatabase db = null;

	/**
	 * 构造函数
	 * 
	 * @context
	 */
	public FaultItemDbManager(Context context) {
		dbHelper = new DbHelper(context);
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * 构造函数
	 */
	public FaultItemDbManager() {
		dbHelper = new DbHelper();
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * 数据库插入操作函数
	 * 
	 * @param date
	 * @param pName
	 * @param tContent
	 */
	public void insertData(String fault_diectory_name, String fault_item_num,
			String fault_item_name, String fault_item_first,
			String fault_item_second, String fault_item_third) {
		// Log.i(TAG, "数据库开始");
		db.execSQL(
				"insert into fault_item_table(fault_diectory_name,fault_item_num,fault_item_name,fault_item_first,fault_item_second,fault_item_third) values(?,?,?,?,?,?);",
				new Object[] { fault_diectory_name, fault_item_num,
						fault_item_name, fault_item_first, fault_item_second,
						fault_item_third });
		// Log.i(TAG, "数据库结束");
	}

	// 插入数据函数

	/**
	 * 查询函数，返回这个表的内容
	 * 
	 * @return
	 */
	public List<FaultItem> query(String str) {
		ArrayList<FaultItem> ptList = new ArrayList<FaultItem>();
		String sql = "select * from fault_item_table where fault_diectory_name=?;";
		Cursor c = db.rawQuery(sql, new String[] { str });
		// Cursor c = db
		// .query("fault_item_table", new String[] {
		// "fault_diectory_name", "fault_item_num",
		// "fault_item_name", "fault_item_first",
		// "fault_item_second", "fault_item_third" },
		// "fault_diectory_name=?", new String[] { str },null, null,
		// null, null);

		// System.out.println(c.moveToNext() + "..." + c.moveToFirst() + "..."
		// + c.moveToLast());

		if (c == null) {
			System.out.println("cursor is null");

		}
		while (c.moveToNext()) {
			FaultItem fi = new FaultItem(c.getInt(c.getColumnIndex("_id")),
					c.getString(c.getColumnIndex("fault_diectory_name")),
					c.getString(c.getColumnIndex("fault_item_num")),
					c.getString(c.getColumnIndex("fault_item_name")),
					c.getString(c.getColumnIndex("fault_item_first")),
					c.getString(c.getColumnIndex("fault_item_second")),
					c.getString(c.getColumnIndex("fault_item_third")));

			// System.out.println(fi.getFault_diectory_name()
			// + "=================");
			// fi是一个表的对象并存储到ptList对象中
			ptList.add(fi);

		}
		c.close();
		closeDB();
		// for (int i = 0; i < ptList.size(); i++) {
		// System.out.println("!!!!!!!"
		// + ptList.get(i).getFault_diectory_name());
		// }
		return ptList;
	}

	/**
	 * 查询函数，返回这个表的内容
	 * 
	 * @return
	 */
	public List<FaultItem> search() {
		ArrayList<FaultItem> ptList = new ArrayList<FaultItem>();
		String sql = "select * from fault_item_table;";
		Cursor c = db.rawQuery(sql, null);
		// Cursor c = db
		// .query("fault_item_table", new String[] {
		// "fault_diectory_name", "fault_item_num",
		// "fault_item_name", "fault_item_first",
		// "fault_item_second", "fault_item_third" },
		// "fault_diectory_name=?", new String[] { str },null, null,
		// null, null);

		// System.out.println(c.moveToNext() + "..." + c.moveToFirst() + "..."
		// + c.moveToLast());

		if (c == null) {
			System.out.println("cursor is null");

		}
		while (c.moveToNext()) {
			FaultItem fi = new FaultItem(c.getInt(c.getColumnIndex("_id")),
					c.getString(c.getColumnIndex("fault_diectory_name")),
					c.getString(c.getColumnIndex("fault_item_num")),
					c.getString(c.getColumnIndex("fault_item_name")),
					c.getString(c.getColumnIndex("fault_item_first")),
					c.getString(c.getColumnIndex("fault_item_second")),
					c.getString(c.getColumnIndex("fault_item_third")));

			// System.out.println(fi.getFault_diectory_name()
			// + "=================");
			// fi是一个表的对象并存储到ptList对象中
			ptList.add(fi);

		}
		c.close();
		closeDB();
		// for (int i = 0; i < ptList.size(); i++) {
		// System.out.println("!!!!!!!"
		// + ptList.get(i).getFault_diectory_name());
		// }
		return ptList;
	}

	/**
	 * 关闭数据库的函数
	 */
	public void closeDB() {
		db.close();
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}

}
