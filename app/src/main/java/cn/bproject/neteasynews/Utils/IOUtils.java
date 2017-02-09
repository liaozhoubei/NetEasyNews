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
	public static String readFromFile(String filename) {
		// "js.txt"
		InputStream inStream = null;
		ByteArrayOutputStream outStream =null;
		try {
			inStream = UIUtils.getContext().getAssets().open(filename);
			outStream = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = inStream.read(bytes)) > 0) {
				outStream.write(bytes, 0, len);
			}
			return outStream.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(inStream);
			close(outStream);
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
