package org.monsterlab.main_activity;

import java.util.ArrayList;

import org.monsterlab.database.FaultItem;
import org.monsterlab.database.FaultItemDbManager;
import org.monsterlab.database.MaintainItem;
import org.monsterlab.database.MaintainItemDbManager;
import org.monsterlab.database.SharedPreferencesDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MaintainItemDetailActivity extends Activity implements
		OnClickListener {
	private TextView itemView;
	private TextView first;
	private Button catalog, returns, pageup, pagedown;
	private Intent intent;
	private MaintainItemDbManager fi_db;
	private ArrayList<MaintainItem> mylist;
	private int _id;
	private boolean flag = false;
	private String diectoryName;
	public SharedPreferencesDB share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设制应用标题不显示
		setContentView(R.layout.activity_maintain_item_detail);

		share = new SharedPreferencesDB();// 存取指定菜单项，为详细菜单返回子菜单提供值

		catalog = (Button) findViewById(R.id.catalog_bt1);
		returns = (Button) findViewById(R.id.return_bt1);
		pageup = (Button) findViewById(R.id.pageup_bt1);
		pagedown = (Button) findViewById(R.id.pagedown_bt1);
		catalog.setOnClickListener(this);
		returns.setOnClickListener(this);
		pageup.setOnClickListener(this);
		pagedown.setOnClickListener(this);

		itemView = (TextView) findViewById(R.id.maintain_item_detail);
		first = (TextView) findViewById(R.id.maintain_item_detail_first);

		intent = getIntent();
		_id = Integer.parseInt(intent.getStringExtra("item_id"));
		diectoryName = intent.getStringExtra("diectoryName");
		share.dateSave("diectoryName", diectoryName,
				MaintainItemDetailActivity.this);
		fi_db = new MaintainItemDbManager(MaintainItemDetailActivity.this);
		mylist = (ArrayList<MaintainItem>) fi_db.query(diectoryName);

		String itemStr = intent.getStringExtra("itemName");
		String myContent = intent.getStringExtra("itemContent");
		// String str = intent.getStringExtra("itemName") + "/n"
		// + intent.getStringExtra("itemContent");
		//
		// System.out.println(str + "..................main" +
		// textView.getId());
		itemView.setText(itemStr);
		first.setText(myContent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.catalog_bt1:
			Intent intent2 = new Intent(MaintainItemDetailActivity.this,
					MaintainDiectoryActivity.class);
			MaintainItemDetailActivity.this.finish();
			startActivity(intent2);
			break;
		case R.id.return_bt1:
			Intent intent3 = new Intent(MaintainItemDetailActivity.this,
					MaintainItemActivity.class);
			MaintainItemDetailActivity.this.finish();
			startActivity(intent3);
			break;
		case R.id.pageup_bt1:
			Intent myintent1 = new Intent(getApplicationContext(),
					MaintainItemDetailActivity.class);
			--_id;
			for (int i = 0; i < mylist.size(); i++) {
				if (_id == mylist.get(i).get_id()) {
					myintent1.putExtra("item_id", new Integer(mylist.get(i)
							.get_id()).toString());
					myintent1.putExtra("diectoryName", mylist.get(i)
							.getMaintain_diectory_name());

					myintent1.putExtra("itemNum", mylist.get(i)
							.getMaintain_item_num());
					myintent1.putExtra("itemName", mylist.get(i)
							.getMaintain_item_name());
					myintent1.putExtra("itemContent", mylist.get(i)
							.getMaintain_item_content());
					flag = true;
				}
			}
			if (flag) {
				MaintainItemDetailActivity.this.finish();
				startActivity(myintent1);
			} else {
				_id++;
			}
			flag = false;
			break;
		case R.id.pagedown_bt1:
			Intent myintent2 = new Intent(getApplicationContext(),
					MaintainItemDetailActivity.class);
			++_id;
			for (int i = 0; i < mylist.size(); i++) {
				if (_id == mylist.get(i).get_id()) {
					myintent2.putExtra("item_id", new Integer(mylist.get(i)
							.get_id()).toString());
					myintent2.putExtra("diectoryName", mylist.get(i)
							.getMaintain_diectory_name());
					myintent2.putExtra("itemNum", mylist.get(i)
							.getMaintain_item_num());
					myintent2.putExtra("itemName", mylist.get(i)
							.getMaintain_item_name());
					myintent2.putExtra("itemContent", mylist.get(i)
							.getMaintain_item_content());
					flag = true;
				}
			}
			if (flag) {
				MaintainItemDetailActivity.this.finish();
				startActivity(myintent2);
			} else {
				_id--;
			}
			flag = false;
			break;
		}

	}

	// 按回车键返回维护指导菜单子菜单
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent1 = new Intent(MaintainItemDetailActivity.this,
					MaintainItemActivity.class);
			MaintainItemDetailActivity.this.finish();
			intent1.putExtra("diectoryName", diectoryName);
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
			Intent intent1 = new Intent(MaintainItemDetailActivity.this,
					MainActivity.class);
			MaintainItemDetailActivity.this.finish();
			startActivity(intent1);
			break;
		case R.id.descend:
			Intent intent2 = new Intent(MaintainItemDetailActivity.this,
					MaintainItemActivity.class);
			MaintainItemDetailActivity.this.finish();
			startActivity(intent2);
			break;
		default:
			break;
		}
		return false;

	}

}
