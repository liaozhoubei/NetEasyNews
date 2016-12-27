package cn.bproject.neteasynews.http;

import org.apache.http.HttpVersion;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClientFactory {
	/** http请求最大并发连接数 */
	private static final int MAX_CONNECTIONS = 10;
	/** 超时时间 */
	private static final int TIMEOUT = 10 * 1000;
	/** 缓存大小 */
	private static final int SOCKET_BUFFER_SIZE = 8 * 1024; // 8KB

	public static DefaultHttpClient create(boolean isHttps) {
		HttpParams params = createHttpParams();
		DefaultHttpClient httpClient = null;
		if (isHttps) {
			// 支持http与https
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
			// ThreadSafeClientConnManager线程安全管理类
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
			httpClient = new DefaultHttpClient(cm, params);
		} else {
			httpClient = new DefaultHttpClient(params);
		}
		return httpClient;
	}

	private static HttpParams createHttpParams() {
		final HttpParams params = new BasicHttpParams();
		// 设置是否启用旧连接检查，默认是开启的。关闭这个旧连接检查可以提高一点点性能，但是增加了I/O错误的风险（当服务端关闭连接时）。
		// 开启这个选项则在每次使用老的连接之前都会检查连接是否可用，这个耗时大概在15-30ms之间
		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);// 设置链接超时时间
		HttpConnectionParams.setSoTimeout(params, TIMEOUT);// 设置socket超时时间
		HttpConnectionParams.setSocketBufferSize(params, SOCKET_BUFFER_SIZE);// 设置缓存大小
		HttpConnectionParams.setTcpNoDelay(params, true);// 是否不使用延迟发送(true为不延迟)
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1); // 设置协议版本
		HttpProtocolParams.setUseExpectContinue(params, true);// 设置异常处理机制
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);// 设置编码
		HttpClientParams.setRedirecting(params, false);// 设置是否采用重定向

		ConnManagerParams.setTimeout(params, TIMEOUT);// 设置超时
		ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(MAX_CONNECTIONS));// 多线程最大连接数
		ConnManagerParams.setMaxTotalConnections(params, 10); // 多线程总连接数
		return params;
	}

}
