package org.monsterlab.main_activity;

import org.monsterlab.copydb.util.DBUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	/**
	 * 如果数据库文件较大，使用FileSplit分割为小于1M的小文件
	 * 本例分割为test_db.001,test_db.002,test_db.003........
	 * 
	 * @see DBUtils#ASSETS_SUFFIX_BEGIN
	 * @see DBUtils#createDataBase()
	 * @see DBUtils#copyDataBase()
	 * @see DBUtils#copyBigDataBase()
	 * 
	 *      要被导入的数据库，置于工程assets目录下
	 */
	public String RESOURCE_DB = "managedb.db";

	/**
	 * 导入到这个数据库里
	 */
	public String DB_NAME = "managedb.db";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设制应用标题不显示
		setContentView(R.layout.activity_main);

		// 数据库文件目标存放的路径(本例为系统默认位置)
		String db_path = "/data"
				+ Environment.getDataDirectory().getAbsolutePath() + "/"
				+ getPackageName() + "/databases/";
		// getFilesDir().getPath() + "data/" + getPackageName() + "/databases/";
		new DBUtils(this, RESOURCE_DB, DB_NAME, db_path).createDataBase();

		/* 实例化各个按钮 */
		Button fault_inquiry = (Button) findViewById(R.id.fault_inquiry);
		Button maintain_guide = (Button) findViewById(R.id.maintain_guide);
		Button photo_send = (Button) findViewById(R.id.photo_send);
		Button logout = (Button) findViewById(R.id.logout);
		Button cpwd = (Button) findViewById(R.id.chang_pwd);

		fault_inquiry.setOnClickListener(this);
		maintain_guide.setOnClickListener(this);
		photo_send.setOnClickListener(this);
		logout.setOnClickListener(this);
		cpwd.setOnClickListener(this);
	}

	/*
	 * 为各个按钮设置监听事件处理 (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.fault_inquiry:
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, FaultDiectoryActivity.class);
			MainActivity.this.finish();
			startActivity(intent);
			break;
		case R.id.maintain_guide:
			Intent intent1 = new Intent();
			intent1.setClass(MainActivity.this, MaintainDiectoryActivity.class);
			MainActivity.this.finish();
			startActivity(intent1);
			break;
		case R.id.photo_send:
			Intent intent2 = new Intent();
			intent2.setClass(MainActivity.this, PhotoActivity.class);
			MainActivity.this.finish();
			startActivity(intent2);
			break;
		case R.id.logout:
			showDialog();
			break;
		case R.id.chang_pwd:
			Intent intent3 = new Intent();
			intent3.setClass(getApplicationContext(),
					UpdatePasswordActivity.class);
			MainActivity.this.finish();
			startActivity(intent3);
			break;
		default:
			break;

		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showDialog();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void showDialog() {
		Dialog dialog = new AlertDialog.Builder(MainActivity.this)
				.setTitle("退出").setMessage("是否退出系统！")
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.this.finish();
						System.exit(0);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).create();
		dialog.show();
	}

}
