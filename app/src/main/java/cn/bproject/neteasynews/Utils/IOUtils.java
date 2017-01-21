package cn.bproject.neteasynews.Utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
	/**
	 * 读取资源文件，将js方法以字符串方法返回
	 *
	 * @return
	 */
	public static String readJS(String filename) {
		// "js.txt"
		try {
			InputStream inStream = UIUtils.getContext().getAssets().open(filename);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = inStream.read(bytes)) > 0) {
				outStream.write(bytes, 0, len);
			}
			return outStream.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e("IOUtils", e);
			}
		}
		return true;
	}
}
