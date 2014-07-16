package org.monsterlab.main_activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("SdCardPath")
public class PhotoActivity extends Activity implements OnClickListener {

	private static final String TAG = "photo";
	private static final int MAX_COUNT = 40;
	protected static final int REQUEST_CODE = 0;
	private String name = null;

	private int screen_width, screen_height;// 屏幕大小

	private Button back, send, photograph;
	private ImageView imageView;

	private Bitmap bitmap = null;
	private Intent data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 设制应用标题不显示
		setContentView(R.layout.photoscreen);

		// 取得窗口宽和高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screen_height = dm.heightPixels;
		screen_width = dm.widthPixels;

		back = (Button) findViewById(R.id.back_bt);
		send = (Button) findViewById(R.id.send_bt);
		photograph = (Button) findViewById(R.id.rephotograph_bt);
		back.setOnClickListener(this);
		send.setOnClickListener(this);
		photograph.setOnClickListener(this);

		imageView = (ImageView) findViewById(R.id.touch);// 用于拍照后显示照片

	}

	/*
	 * 为三个按钮实现监听事件back：返回 send：发送 photograph:拍照
	 */
	@Override
	public void onClick(View v) {
		// Log.e(TAG, "" + v.getId());
		switch (v.getId()) {
		case R.id.back_bt:
			// Log.e(TAG, "aaaaaaa");
			Intent intent1 = new Intent(PhotoActivity.this, MainActivity.class);
			PhotoActivity.this.finish();
			startActivity(intent1);
			break;
		case R.id.rephotograph_bt:
			// Log.e(TAG, "ccccccc");
			Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			Uri imageUri = Uri.fromFile(new File(Environment
					.getExternalStorageDirectory(), "tankfault.jpg"));
			// 指定照片保存路径（SD卡），workupload.jpg为一个临时文件，每次拍照后这个图片都会被替换
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(cameraIntent, REQUEST_CODE);
			// 打开相机，照片并且保存到bufferPhoto.jpg缓存临时文件，
			// 照片不缩小
			break;
		case R.id.send_bt:
			// Log.e(TAG, "bbbbbbb");
			if (bitmap != null) {
				savePhotoToLocal(data, bitmap);// save photo to sdcard
				Intent intent = new Intent(Intent.ACTION_SEND);
				// 图片分享
				intent.setType("image/*");
				// 添加图片
				File f = new File("/sdcard/tankfault.jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_STREAM, u); // 添加图片
				intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
				intent.putExtra(Intent.EXTRA_TEXT,
						"I would like to share this with you...");
				startActivity(Intent.createChooser(intent, "请选择传送方式"));
			} else {
				Toast.makeText(this, "请拍完照后发送！", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {// result is not correct
			return;
		} else {
			if (requestCode == REQUEST_CODE) {
				// 将保存在本地的图片取出并缩小后显示在界面上
				Bitmap camorabitmap = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory() + "/tankfault.jpg");

				if (null != camorabitmap) {
					// 下面这两句是对图片按照一定的比例缩放，这样就可以完美地显示出来。
					int scale = ImageThumbnail.reckonThumbnail(
							camorabitmap.getWidth(), camorabitmap.getHeight(),
							screen_width, screen_height);
					// 获取比例
					this.bitmap = ImageThumbnail.tailorThumbnail(camorabitmap,
							camorabitmap.getWidth() / scale,
							camorabitmap.getHeight() / scale);
					/*
					 * 这里后两个参数，若scale为1则参数不变，即为获得的尺寸，若scale大于1
					 * ，参数大小变为设定的尺寸大小。返回一个剪裁完的图片
					 */
					// 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
					// camorabitmap.recycle();
					// 将处理过的图片显示在界面上，并保存到本地
					// imageView.setVisibility(View.VISIBLE);
					this.data = data;
					if (bitmap != null) {
						imageView.setImageBitmap(bitmap);
						imageView.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(PhotoActivity.this,
										ImageEnlargedActivity.class);
								intent.putExtra(
										"image_path",
										Environment
												.getExternalStorageDirectory()
												+ "/tankfault.jpg");
								startActivity(intent);

							}
						});
					}
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public void savePhotoToLocal(Intent data, Bitmap bMap) {
		// 如果文件夹不存在则创建文件夹，并将bitmap图像文件保存
		String sdStatus = Environment.getExternalStorageState();
		FileOutputStream fileOut = null;
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			Log.i("TestFile", "SD card is not avaiable/writeable right now.");
			return;
		}

		File file = new File("/sdcard/myImage/");
		if (!file.exists())
			file.mkdirs();// 创建文件夹
		String fileName = "/sdcard/myImage/" + name;
		try {
			// 将bitmap转为jpg文件保存
			fileOut = new FileOutputStream(fileName);
			bMap.compress(CompressFormat.JPEG, 100, fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 按回车键返回菜单
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent1 = new Intent(PhotoActivity.this, MainActivity.class);
			PhotoActivity.this.finish();
			startActivity(intent1);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}

class ImageThumbnail {

	public static int reckonThumbnail(int oldWidth, int oldHeight,
			int newWidth, int newHeight) {// 两个if语句判断存储的图片是否大于设定的大小，不满足则记录下存储图与规定
											// 大小之间的比例
		if ((oldHeight > newHeight && oldWidth > newWidth)
				|| (oldHeight <= newHeight && oldWidth > newWidth)) {
			int scale1 = (int) (oldWidth / (float) newWidth);
			if (scale1 <= 1)
				scale1 = 1;
			return scale1;
		} else if (oldHeight > newHeight && oldWidth <= newWidth) {
			int scale2 = (int) (oldHeight / (float) newHeight);
			if (scale2 <= 1)
				scale2 = 1;
			return scale2;
		}
		return 1;
	}

	public static Bitmap tailorThumbnail(Bitmap bMap, int width, int height) {
		int bMapWidth = bMap.getWidth();
		int bMapHeght = bMap.getHeight();
		Matrix matrix = new Matrix();// 创建矩阵类对象
		matrix.postScale((float) width / bMapWidth, (float) height / bMapHeght);
		// Postconcats the matrix with the specified scale. M' = S(sx, sy) * M
		return Bitmap.createBitmap(bMap, 0, 0, bMapWidth, bMapHeght, matrix,
				true);
		/*
		 * public static Bitmap createBitmap (Bitmap source, int x, int y, int
		 * width, int height, Matrix m, boolean filter)
		 * 从原始位图剪切图像，这是一种高级的方式。可以用Matrix(矩阵)来实现旋转等高级方式截图 参数说明： 　　Bitmap
		 * source：要从中截图的原始位图 　　int x:起始x坐标 　　int y：起始y坐标 int width：要截的图的宽度 int
		 * height：要截的图的宽度 Bitmap.Config config：一个枚举类型的配置，可以定义截到的新位图的质量
		 * 返回值：返回一个剪切好的Bitmap
		 */
	}

}
