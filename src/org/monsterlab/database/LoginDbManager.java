package org.monsterlab.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库管理类，对数据库进行插入、删除、查询操作
 * 
 */
public class LoginDbManager {

	private DbHelper dbHelper = null;
	private SQLiteDatabase db = null;

	/**
	 * 构造函数
	 * 
	 * @context
	 */
	public LoginDbManager(Context context) {
		dbHelper = new DbHelper(context);
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * 构造函数
	 */
	public LoginDbManager() {
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
	public void insertData(String pwd) {
		// Log.i(TAG, "数据库开始");
		db.execSQL("insert into login_table(password) values(?);",
				new Object[] { pwd });
		// Log.i(TAG, "数据库结束");
	}

	// 插入数据函数

	/**
	 * 查询函数，返回这个表的内容
	 * 
	 * @return
	 */
	public List<User> query() {
		ArrayList<User> fdList = new ArrayList<User>();
		Cursor c = db.rawQuery("SELECT * FROM login_table;", null);

		if (c == null) {
			System.out.println("cursor is null");

		}
		while (c.moveToNext()) {
			User fd = new User(c.getInt(c.getColumnIndex("_id")),c.getString(c.getColumnIndex("password")));
			// fd是一个表的对象并存储到ptList对象中
			fdList.add(fd);

		}
		c.close();
		return fdList;
	}
	
	public void update(String pwd,String newpwd){
		ContentValues cv=new ContentValues();
		cv.put("password", newpwd);
		db.update("login_table", cv, "password=?", new String[]{pwd});
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
