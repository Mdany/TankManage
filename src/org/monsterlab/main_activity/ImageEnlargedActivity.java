package org.monsterlab.main_activity;

import org.monsterlab.image_manage.ZoomImageView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Window;

/**
 * 显示放大的图
 * 
 */
public class ImageEnlargedActivity extends Activity {

	/**
	 * 自定义的ImageView控制，可对图片进行多点触控缩放和拖动
	 */
	private ZoomImageView zoomImageView;

	/**
	 * 待展示的图片
	 */
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_enlarged);
		zoomImageView = (ZoomImageView) findViewById(R.id.zoom_image_view);
		// 取出图片路径，并解析成Bitmap对象，然后在ZoomImageView中显示
		String imagePath = getIntent().getStringExtra("image_path");
		System.out.println(imagePath);
		bitmap = BitmapFactory.decodeFile(imagePath);
//		System.out.println("..............");
		if (bitmap != null) {
//			System.out.println("not null");
			zoomImageView.setImageBitmap(bitmap);
		}else{
//			System.out.println("bitmap is null"+bitmap);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 记得将Bitmap对象回收掉
		if (bitmap != null) {
			bitmap.recycle();
		}
	}

}