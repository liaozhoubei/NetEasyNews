package cn.bproject.neteasynews.http;

/**
 * Created by Bei on 2017/1/6.
 * 根据网络返回的状态码返回相应的状态
 */

public class HttpRequestCode {
    //    100 Continue
//    初始的请求已经接受，客户应当继续发送请求的其余部分
//    101 Switching Protocols
//    服务器将遵从客户的请求转换到另外一种协议
//    200 OK
//    一切正常，对GET和POST请求的应答文档跟在后面
//    201 Created
//    服务器已经创建了文档，Location头给出了它的URL。
//            202 Accepted
//    已经接受请求，但处理尚未完成。
//            203 Non-Authoritative Information
//    文档已经正常地返回，但一些应答头可能不正确，因为使用的是文档的拷贝
//    204 No Content
//    没有新文档，浏览器应该继续显示原来的文档。如果用户定期地刷新页面，而Servlet可以确定用户文档足够新，这个状态代码是很有用的
//    205 Reset Content
//    没有新的内容，但浏览器应该重置它所显示的内容。用来强制浏览器清除表单输入内容
//    206 Partial Content
//    客户发送了一个带有Range头的GET请求，服务器完成了它
//    300 Multiple Choices
//    客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出。如果服务器要提出优先选择，则应该在Location应答头指明。
//            301 Moved Permanently
//    客户请求的文档在其他地方，新的URL在Location头中给出，浏览器应该自动地访问新的URL。
//            302 Found
//    类似于301，但新的URL应该被视为临时性的替代，而不是永久性的。
//            303 See Other
//    类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取
//    304 Not Modified
//    客户端有缓冲的文档并发出了一个条件性的请求（一般是提供If-Modified-Since头表示客户只想比指定日期更新的文档）。服务器告诉客户，原来缓冲的文档还可以继续使用。
//            305 Use Proxy
//    客户请求的文档应该通过Location头所指明的代理服务器提取
//    307 Temporary Redirect
//    和302（Found）相同。许多浏览器会错误地响应302应答进行重定向，即使原来的请求是 POST，即使它实际上只能在POST请求的应答是303时才能重定向。由于这个原因，HTTP 1.1新增了307，以便更加清除地区分几个状态代码： 当出现303应答时，浏览器可以跟随重定向的GET和POST请求；如果是307应答，则浏览器只能跟随对GET请求的重定向。
//            400 Bad Request
//    请求出现语法错误。
//            401 Unauthorized
//    客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框，然后在填写合适的Authorization头后再次发出请求。
//            403 Forbidden
//    资源不可用。
//            404 Not Found
//    无法找到指定位置的资源
//    405 Method Not Allowed
//    请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用。
//            406 Not Acceptable
//    指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容
//    407 Proxy Authentication Required
//    类似于401，表示客户必须先经过代理服务器的授权。
//            408 Request Timeout
//    在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。
//            409 Conflict
//    通常和PUT请求有关。由于请求和资源的当前状态相冲突，因此请求不能成功。
//            410 Gone
//    所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407表示文档永久地离开了指定的位置，而404表示由于未知的原因文档不可用。
//            411 Length Required
//    服务器不能处理请求，除非客户发送一个Content-Length头。
//            412 Precondition Failed
//    请求头中指定的一些前提条件失败
//    413 Request Entity Too Large
//    目标文档的大小超过服务器当前愿意处理的大小。如果服务器认为自己能够稍后再处理该请求，则应该提供一个Retry-After头
//    414 Request URI Too Long
//    URI太长
//    416 Requested Range Not Satisfiable
//    服务器不能满足客户在请求中指定的Range头
//    500 Internal Server Error
//    服务器遇到了意料不到的情况，不能完成客户的请求
//    501 Not Implemented
//    服务器不支持实现请求所需要的功能。例如，客户发出了一个服务器不支持的PUT请求
//    502 Bad Gateway
//    服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答
//    503 Service Unavailable
//    服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头
//    504 Gateway Timeout
//    由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答
//    505 HTTP Version Not Supported
//    服务器不支持请求中所指明的HTTP版本
    public static String ReturnCode(int code) {
        String result = null;
        switch (code) {
            case 100:
                result = "requestCode: 100 -- Continue";
                break;
            case 101:
                result = "requestCode: 101 -- Switching Protocols";
                break;
//            case 200:
//                result = "OK";
//                break;
            case 201:
                result = "requestCode: 201 -- Created";
                break;
            case 202:
                result = "requestCode: 202 -- Accepted";
                break;
            case 203:
                result = "requestCode: 203 -- Non-Authoritative Information";
                break;
            case 204:
                result = "requestCode: 204 -- No Content";
                break;
            case 205:
                result = "requestCode: 205 -- Reset Content";
                break;
            case 206:
                result = "requestCode: 206 -- Partial Content";
                break;
            case 300:
                result = "requestCode: 300 -- Multiple Choices";
                break;
            case 301:
                result = "requestCode: 301 -- Moved Permanently";
                break;
            case 302:
                result = "requestCode: 302 -- Found";
                break;
            case 303:
                result = "requestCode: 303 -- See Other";
                break;
            case 304:
                result = "requestCode: 304 -- Not Modified";
                break;
            case 305:
                result = "requestCode: 305 -- Use Proxy";
                break;
            case 307:
                result = "requestCode: 307 -- Temporary Redirect";
                break;
            case 400:
                result = "requestCode: 400 -- Bad Request";
                break;
            case 401:
                result = "requestCode: 401 -- Unauthorized";
                break;
            case 403:
                result = "requestCode: 403 -- Forbidden";
                break;
            case 404:
                result = "requestCode: 404 -- Not Found";
                break;
            case 405:
                result = "requestCode: 405 -- Method Not Allowed";
                break;
            case 406:
                result = "requestCode: 406 -- Not Acceptable";
                break;
            case 407:
                result = "requestCode: 407 -- Proxy Authentication Required";
                break;
            case 408:
                result = "requestCode: 408 -- Request Timeout";
                break;
            case 409:
                result = "requestCode: 409 -- Conflict";
                break;
            case 410:
                result = "requestCode: 410 -- Gone";
                break;
            case 411:
                result = "requestCode: 411 -- Length Required";
                break;
            case 412:
                result = "requestCode: 412 -- Precondition Failed";
                break;
            case 413:
                result = "requestCode: 413 -- Request Entity Too Large";
                break;
            case 414:
                result = "requestCode: 414 -- Request URI Too Long";
                break;
            case 416:
                result = "requestCode: 416 -- Requested Range Not Satisfiable";
                break;
            case 500:
                result = "requestCode: 500 -- Internal Server Error";
                break;
            case 501:
                result = "requestCode: 501 -- Not Implemented";
                break;
            case 502:
                result = "requestCode: 502 -- Bad Gateway";
                break;
            case 503:
                result = "requestCode: 503 -- Service Unavailable";
                break;
            case 504:
                result = "requestCode: 504 -- Gateway Timeout";
                break;
            case 505:
                result = "requestCode: 505 -- HTTP Version Not Supported";
                break;
            default:
                break;
        }
        return result;
    }
}
