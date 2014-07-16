package org.monsterlab.main_activity;

import java.util.ArrayList;
import java.util.HashMap;

import org.monsterlab.database.MaintainDiectory;
import org.monsterlab.database.MaintainDiectoryDbManager;
import org.monsterlab.database.SharedPreferencesDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
public class MaintainDiectoryActivity extends Activity {

	private ListView listView;

	/**
	 * “维护指导书目录” 数据库管理对象
	 */
	private MaintainDiectoryDbManager md_db;
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

		md_db = new MaintainDiectoryDbManager(MaintainDiectoryActivity.this);
		ArrayList<MaintainDiectory> mylist = (ArrayList<MaintainDiectory>) md_db
				.query();

		for (int i = 0; i < mylist.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("itemNum", mylist.get(i).getMaintain_num());
			map.put("itemName", mylist.get(i).getMaintain_name());
			map.put("itemName1", "\t\t" + mylist.get(i).getMaintain_name());
			myArrayList.add(map);
		}

		// 生成SimpleAdapter适配器对象
		SimpleAdapter mySimpleAdapter = new SimpleAdapter(this, myArrayList,// 数据源
				R.layout.list_maintain_diectory,// ListView内部数据展示形式的布局文件listitem.xml
				new String[] {"itemName1" },// HashMap中的两个key值
				new int[] {R.id.list_maintain_diectory_name });/*
															 * 布局文件listitem.
															 * xml中组件的id
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
		searchView.setOnSearchClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent0 = new Intent(MaintainDiectoryActivity.this,
						MaintainMiddleActivity.class);
				MaintainDiectoryActivity.this.finish();
				startActivity(intent0);
			}
		});
		// searchView.setOnQueryTextListener(new OnQueryTextListener() {
		//
		// @Override
		// public boolean onQueryTextSubmit(String query) {
		// // TODO Auto-generated method stub
		// Intent intent0 = new Intent(MaintainDiectoryActivity.this,
		// MaintainMiddleActivity.class);
		// MaintainDiectoryActivity.this.finish();
		// startActivity(intent0);
		// return false;
		// }
		//
		// @Override
		// public boolean onQueryTextChange(String newText) {
		// // TODO Auto-generated method stub
		// if (newText.length() != 0) {
		// listView.setFilterText(newText);
		// } else {
		// listView.clearTextFilter();
		// }
		// return false;
		// }
		// });
		// 添加点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 获得选中项的HashMap对象
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) listView
						.getItemAtPosition(arg2);
//				String num = map.get("itemNum");
				String name = map.get("itemName");

				// 为item界面传值，传被点击选中目录名字，并根据名字查询显示该目录下的子项
				Intent intent = new Intent(getApplicationContext(),
						MaintainItemActivity.class);
				// intent.putExtra("diectoryName", name);
				share.dateSave("diectoryName", name, MaintainDiectoryActivity.this);

				// System.out.println(name+"................");

				MaintainDiectoryActivity.this.finish();
				startActivity(intent);
			}

		});
		md_db.closeDB();
	}

	// 按回车键返回主界面
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent1 = new Intent(MaintainDiectoryActivity.this,
					MainActivity.class);
			MaintainDiectoryActivity.this.finish();
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
			Intent intent1 = new Intent(MaintainDiectoryActivity.this,
					MainActivity.class);
			MaintainDiectoryActivity.this.finish();
			startActivity(intent1);
			break;
		case R.id.descend:
			Intent intent2 = new Intent(MaintainDiectoryActivity.this,
					MainActivity.class);
			MaintainDiectoryActivity.this.finish();
			startActivity(intent2);
			break;
		default:
			break;
		}
		return false;

	}
}
