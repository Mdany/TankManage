package org.monsterlab.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper的子类，实现创建数据库，更新数据库，关闭数据库的功能
 * 
 */
public class DbHelper extends SQLiteOpenHelper {
	private final static String DB_NAME = "managedb.db";
	private static int DB_VERSION = 1;
	private String sql_fault_diectory = null;
	private String sql_fault_item = null;
	private String sql_maintain_diectory = null;
	private String sql_maintain_item = null;
	private String sql_login=null;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * 构造函数
	 * 
	 * @context
	 */
	public DbHelper() {
		super(null, DB_NAME, null, DB_VERSION);
	}

	/**
	 * 构造函数
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		sql_fault_diectory = "create table IF NOT EXISTS fault_diectory_table(_id integer primary key  autoincrement ,"
				+ "fault_num varchar(50)," + "fault_name varchar(50));";
		sql_fault_item = "create table IF NOT EXISTS fault_item_table(_id integer primary key  autoincrement ,"
				+ "fault_diectory_name varchar(50),"
				+ "fault_item_num varchar(50),"
				+ "fault_item_name varchar(50),"
				+ "fault_item_first varchar(255),"
				+ "fault_item_second varchar(255),"
				+ "fault_item_third varchar(255));";
		sql_maintain_diectory = "create table IF NOT EXISTS maintain_diectory_table(_id integer primary key  autoincrement ,"
				+ "maintain_num varchar(50)," + "maintain_name varchar(50));";
		sql_maintain_item = "create table IF NOT EXISTS maintain_item_table(_id integer primary key  autoincrement ,"
				+ "maintain_diectory_name varchar(50),"
				+ "maintain_item_num varchar(50),"
				+ "maintain_item_name varchar(50),"
				+ "maintain_item_content varchar(255));";
		sql_login="create table IF NOT EXISTS login_table(_id integer primary key  autoincrement ,"
				+"password varchar(100));";
		db.execSQL(sql_fault_diectory);
		db.execSQL(sql_fault_item);
		db.execSQL(sql_maintain_diectory);
		db.execSQL(sql_maintain_item);
		db.execSQL(sql_login);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		super.close();
	}

}
