package org.monsterlab.main_activity;

import java.util.ArrayList;

import org.monsterlab.database.FaultItem;
import org.monsterlab.database.FaultItemDbManager;
import org.monsterlab.database.SharedPreferencesDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FaultItemDetailActivity extends Activity implements
		OnClickListener {
	public String Tag="FaultItemDetailActivity";
	private TextView itemView;
	private TextView first;
	private TextView second;
	private TextView third;
	private Button catalog, returns, pageup, pagedown;
	private Intent intent;
	private FaultItemDbManager fi_db;
	private ArrayList<FaultItem> mylist;
	private int _id;
	private boolean flag = false;
	private String diectoryName;
	public SharedPreferencesDB share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设制应用标题不显示
		setContentView(R.layout.activity_fault_item_detail);
		
		share = new SharedPreferencesDB();// 存取指定菜单项，为详细菜单返回子菜单提供值
		
		catalog = (Button) findViewById(R.id.catalog_bt);
		returns = (Button) findViewById(R.id.return_bt);
		pageup = (Button) findViewById(R.id.pageup_bt);
		pagedown = (Button) findViewById(R.id.pagedown_bt);
		catalog.setOnClickListener(this);
		returns.setOnClickListener(this);
		pageup.setOnClickListener(this);
		pagedown.setOnClickListener(this);

		itemView = (TextView) findViewById(R.id.fault_item_detail);
		first=(TextView) findViewById(R.id.fault_item_detail_first);
		second=(TextView) findViewById(R.id.fault_item_detail_second);
		third=(TextView) findViewById(R.id.fault_item_detail_third);

		intent = getIntent();
		_id = Integer.parseInt(intent.getStringExtra("item_id"));
		diectoryName = intent.getStringExtra("diectoryName");
		share.dateSave("diectoryName", diectoryName, FaultItemDetailActivity.this);
		
		fi_db = new FaultItemDbManager(FaultItemDetailActivity.this);
		mylist = (ArrayList<FaultItem>) fi_db.query(diectoryName);

		String itemStr=intent.getStringExtra("itemName");
		String myFirst=intent.getStringExtra("itemFirst");
		String mySecond=intent.getStringExtra("itemSecond");
		String myThird=intent.getStringExtra("itemThird");
//		String str = intent.getStringExtra("itemNum") + "/n"
//				+ intent.getStringExtra("itemName") + "/n"
//				+ intent.getStringExtra("itemFirst") + "/n"
//				+ intent.getStringExtra("itemSecond") + "/n"
//				+ intent.getStringExtra("itemThird");
//		itemView.setMovementMethod(ScrollingMovementMethod.getInstance());
		itemView.setText(itemStr);
		first.setText(myFirst);
		second.setText(mySecond);
		third.setText(myThird);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.catalog_bt:
			Intent intent2 = new Intent(FaultItemDetailActivity.this,
					FaultDiectoryActivity.class);
			FaultItemDetailActivity.this.finish();
			startActivity(intent2);
			break;
		case R.id.return_bt:
			Intent intent3 = new Intent(FaultItemDetailActivity.this,
					FaultItemActivity.class);
			FaultItemDetailActivity.this.finish();
			startActivity(intent3);
			break;
		case R.id.pageup_bt:
			Intent myintent1 = new Intent(getApplicationContext(),
					FaultItemDetailActivity.class);
			--_id;
			for (int i = 0; i < mylist.size(); i++) {
				Log.e(Tag, "="+mylist.get(i).get_id());
				if (_id == mylist.get(i).get_id()) {
					Log.e(Tag, "aaaaa---");
					myintent1.putExtra("item_id", new Integer(mylist.get(i).get_id()).toString());
					myintent1.putExtra("diectoryName", mylist.get(i)
							.getFault_diectory_name());

					myintent1.putExtra("itemNum", mylist.get(i)
							.getFault_item_num());
					myintent1.putExtra("itemName", mylist.get(i)
							.getFault_item_name());
					myintent1.putExtra("itemFirst", mylist.get(i)
							.getFault_item_first());
					myintent1.putExtra("itemSecond", mylist.get(i)
							.getFault_item_second());
					myintent1.putExtra("itemThird", mylist.get(i)
							.getFault_item_third());
					flag = true;
				}
			}
			if (flag) {
				FaultItemDetailActivity.this.finish();
				startActivity(myintent1);
			} else {
				Log.e(Tag, "abbbbb="+_id);
				_id++;
			}
			flag = false;
			break;
		case R.id.pagedown_bt:
			Intent myintent2 = new Intent(getApplicationContext(),
					FaultItemDetailActivity.class);
			++_id;
			for (int i = 0; i < mylist.size(); i++) {
				if (_id == mylist.get(i).get_id()) {
					myintent2.putExtra("item_id", new Integer(mylist.get(i).get_id()).toString());
					myintent2.putExtra("diectoryName", mylist.get(i)
							.getFault_diectory_name());
					myintent2.putExtra("itemNum", mylist.get(i)
							.getFault_item_num());
					myintent2.putExtra("itemName", mylist.get(i)
							.getFault_item_name());
					myintent2.putExtra("itemFirst", mylist.get(i)
							.getFault_item_first());
					myintent2.putExtra("itemSecond", mylist.get(i)
							.getFault_item_second());
					myintent2.putExtra("itemThird", mylist.get(i)
							.getFault_item_third());
					flag = true;
				}
			}
			if (flag) {
				FaultItemDetailActivity.this.finish();
				startActivity(myintent2);
			} else {
				_id--;
			}
			flag = false;
			break;
		}

	}

	// 按回车键返回故障数据库子菜单
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent1 = new Intent(FaultItemDetailActivity.this,
					FaultItemActivity.class);
			FaultItemDetailActivity.this.finish();
			startActivity(intent1);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 设置菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.main_menu:
			Intent intent1 = new Intent(FaultItemDetailActivity.this,
					MainActivity.class);
			FaultItemDetailActivity.this.finish();
			startActivity(intent1);
			break;
		case R.id.descend:
			Intent intent2 = new Intent(FaultItemDetailActivity.this,
					FaultItemActivity.class);
			FaultItemDetailActivity.this.finish();
			startActivity(intent2);
			break;
		default:
			break;
		}
		return false;

	}

}
