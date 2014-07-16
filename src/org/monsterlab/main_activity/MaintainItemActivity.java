package org.monsterlab.main_activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.monsterlab.database.MaintainItem;
import org.monsterlab.database.MaintainItemDbManager;
import org.monsterlab.database.SharedPreferencesDB;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * 维护指导目录显示界面，可选择需要分类，查看具体项
 * 
 * @author hmj
 * 
 */
public class MaintainItemActivity extends Activity {

	private ListView listView;

	/**
	 * “维护指导书目录” 数据库管理对象
	 */
	private MaintainItemDbManager mi_db;
	public SharedPreferencesDB share;
	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设制应用标题不显示
		setContentView(R.layout.activity_maintain);

		share = new SharedPreferencesDB();// 存取指定菜单项，为详细菜单返回子菜单提供值

		// ListView对象
		listView = (ListView) findViewById(R.id.maintain_diectory_layout);
		// 创建ArrayList对象 并添加数据
		ArrayList<HashMap<String, String>> myArrayList = new ArrayList<HashMap<String, String>>();

		Intent intent = getIntent();
		// String maintain_diectory_name =
		// intent.getStringExtra("diectoryName");
		String maintain_diectory_name = share.getDate("diectoryName", "", this);
		// System.out.println(maintain_diectory_name);

		mi_db = new MaintainItemDbManager(MaintainItemActivity.this);
		ArrayList<MaintainItem> mylist = (ArrayList<MaintainItem>) mi_db
				.query(maintain_diectory_name);

		for (int i = 0; i < mylist.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();

			map.put("diectoryName", mylist.get(i).getMaintain_diectory_name());
			map.put("item_id", "" + mylist.get(i).get_id());
			map.put("itemNum", "" +"第"+ mylist.get(i).getMaintain_item_num()+"节：");
			map.put("itemName", mylist.get(i).getMaintain_item_name());
			map.put("itemContent", mylist.get(i).getMaintain_item_content());
			myArrayList.add(map);
		}

		// 生成SimpleAdapter适配器对象
		SimpleAdapter mySimpleAdapter = new SimpleAdapter(this, myArrayList,// 数据源
				R.layout.list_maintain_item,// ListView内部数据展示形式的布局文件listitem.xml
				new String[] { "itemNum", "itemName", },// HashMap中的两个key值
				new int[] { R.id.list_maintain_item_num,
						R.id.list_maintain_item_name });/*
														 * 布局文件listitem.xml中组件的id
														 * 布局文件的各组件分别映射到HashMap的各元素上
														 * ，完成适配
														 */

		listView.setAdapter(mySimpleAdapter);
		listView.setTextFilterEnabled(true);

		// 实现搜索功能
		searchView = (SearchView) findViewById(R.id.searchView1);
		// searchView.setIconifiedByDefault(false);
		searchView.setQueryHint("输入搜索内容");
		searchView.setSubmitButtonEnabled(true);
		searchView.setSubmitButtonEnabled(true);
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				if (newText.length() != 0) {
					listView.setFilterText(newText);
				} else {
					listView.clearTextFilter();
				}
				return false;
			}
		});
		// 添加点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("UseValueOf")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 获得选中项的HashMap对象
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getItemAtPosition(arg2);
				String num = map.get("itemNum");
				String name = map.get("itemName");

				// 传给下一个界面该子项的信息
				Intent intent = new Intent(getApplicationContext(),
						MaintainItemDetailActivity.class);
				intent.putExtra("item_id",
						new Integer(map.get("item_id")).toString());
				intent.putExtra("diectoryName", map.get("diectoryName"));
				intent.putExtra("itemName", name);
				intent.putExtra("itemContent", map.get("itemContent"));

				Toast.makeText(
						getApplicationContext(),
						"你选择了第" + arg2 + "itemNum的值是:" + num + "itemName的值是:"
								+ name, Toast.LENGTH_SHORT).show();
				MaintainItemActivity.this.finish();
				startActivity(intent);
			}

		});
		mi_db.closeDB();
	}

	// 按回车键返回维护指导菜单
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent1 = new Intent(MaintainItemActivity.this,
					MaintainDiectoryActivity.class);
			MaintainItemActivity.this.finish();
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
			Intent intent1 = new Intent(MaintainItemActivity.this,
					MainActivity.class);
			MaintainItemActivity.this.finish();
			startActivity(intent1);
			break;
		case R.id.descend:
			Intent intent2 = new Intent(MaintainItemActivity.this,
					MaintainDiectoryActivity.class);
			MaintainItemActivity.this.finish();
			startActivity(intent2);
			break;
		default:
			break;
		}
		return false;

	}

}
