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
public class MaintainItemDbManager {

	private DbHelper dbHelper = null;
	private SQLiteDatabase db = null;

	/**
	 * 构造函数
	 * 
	 * @context
	 */
	public MaintainItemDbManager(Context context) {
		dbHelper = new DbHelper(context);
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * 构造函数
	 */
	public MaintainItemDbManager() {
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
	public void insertData(String maintain_diectory_name,
			String maintain_item_num, String maintain_item_name) {
		// Log.i(TAG, "数据库开始");
		db.execSQL(
				"insert into maintain_item_table(maintain_diectory_name,maintain_item_num,maintain_item_name,maintain_item_content) values(?,?,?,?);",
				new Object[] { maintain_diectory_name, maintain_item_num,
						maintain_item_name });
		// Log.i(TAG, "数据库结束");
	}

	// 插入数据函数

	/**
	 * 查询函数，返回这个表的内容
	 * 
	 * @return
	 */
	public List<MaintainItem> query(String str) {
		ArrayList<MaintainItem> ptList = new ArrayList<MaintainItem>();
		String sql = "select * from maintain_item_table where maintain_diectory_name=?;";
		Cursor c = db.rawQuery(sql, new String[] { str });

		// System.out.println(c.moveToNext() + "..." + c.moveToFirst() + "..."
		// + c.moveToLast());

		if (c == null) {
			System.out.println("cursor is null");

		}
		while (c.moveToNext()) {
			MaintainItem fi = new MaintainItem(
					c.getInt(c.getColumnIndex("_id")), c.getString(c
							.getColumnIndex("maintain_diectory_name")),
					c.getString(c.getColumnIndex("maintain_item_num")),
					c.getString(c.getColumnIndex("maintain_item_name")),
					c.getString(c.getColumnIndex("maintain_item_content")));

			// System.out.println(fi.getMaintain_diectory_name()
			// + "=================");

			// fi是一个表的对象并存储到ptList对象中
			ptList.add(fi);

		}
		c.close();
		closeDB();

		// System.out.println(ptList.size()+">>>>>>>>>>>>>>>>>>>>>>");

		return ptList;
	}

	/**
	 * 查询函数，返回这个表的内容
	 * 
	 * @return
	 */
	public List<MaintainItem> search() {
		ArrayList<MaintainItem> ptList = new ArrayList<MaintainItem>();
		String sql = "select * from maintain_item_table;";
		Cursor c = db.rawQuery(sql, null);

		// System.out.println(c.moveToNext() + "..." + c.moveToFirst() + "..."
		// + c.moveToLast());

		if (c == null) {
			System.out.println("cursor is null");

		}
		while (c.moveToNext()) {
			MaintainItem fi = new MaintainItem(
					c.getInt(c.getColumnIndex("_id")), c.getString(c
							.getColumnIndex("maintain_diectory_name")),
					c.getString(c.getColumnIndex("maintain_item_num")),
					c.getString(c.getColumnIndex("maintain_item_name")),
					c.getString(c.getColumnIndex("maintain_item_content")));

			// System.out.println(fi.getMaintain_diectory_name()
			// + "=================");

			// fi是一个表的对象并存储到ptList对象中
			ptList.add(fi);

		}
		c.close();
		closeDB();

		// System.out.println(ptList.size()+">>>>>>>>>>>>>>>>>>>>>>");

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
