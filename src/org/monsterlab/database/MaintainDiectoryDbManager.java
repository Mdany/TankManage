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
public class MaintainDiectoryDbManager {

	private DbHelper dbHelper = null;
	private SQLiteDatabase db = null;

	/**
	 * 构造函数
	 * 
	 * @context
	 */
	public MaintainDiectoryDbManager(Context context) {
		dbHelper = new DbHelper(context);
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * 构造函数
	 */
	public MaintainDiectoryDbManager() {
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
	public void insertData(String maintain_num, String maintain_name) {
		// Log.i(TAG, "数据库开始");
		db.execSQL(
				"insert into maintain_diectory_table(maintain_num,maintain_name) values(?,?);",
				new Object[] { maintain_num, maintain_name });
		// Log.i(TAG, "数据库结束");
	}

	// 插入数据函数

	/**
	 * 查询函数，返回这个表的内容
	 * 
	 * @return
	 */
	public List<MaintainDiectory> query() {
		ArrayList<MaintainDiectory> fdList = new ArrayList<MaintainDiectory>();
		Cursor c = db.rawQuery("SELECT * FROM maintain_diectory_table;", null);

		if (c == null) {
			System.out.println("cursor is null");

		}
		while (c.moveToNext()) {
			MaintainDiectory md = new MaintainDiectory(c.getInt(c
					.getColumnIndex("_id")), c.getString(c
					.getColumnIndex("maintain_num")), c.getString(c
					.getColumnIndex("maintain_name")));
			// md是一个表的对象并存储到ptList对象中
			fdList.add(md);

		}
		c.close();
		closeDB();
		return fdList;
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
