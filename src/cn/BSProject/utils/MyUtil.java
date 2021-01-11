package cn.BSProject.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BSProject.Entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 我的工具类
 *
 * @author Lld
 */
public class MyUtil {
    User user = new User();


    // ----------------------------------------------------------------------------------------------------------


    /**
     * 封装获取json请求的方法名参数
     */
    public static User getjsonmethod(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 此方法只能正确处理json格式的数据请求，而且必须带的参数是{method：xxx}
        try {
            System.out.println("请求收到");// 测试请求是否走进来了


            // 获取请求行的相关信息
            //  System.out.println("getMethod : " + request.getMethod());//该方法用于获取 HTTP 请求消息中的请求方式（如 GET、POST 等）
            // System.out.println("getRequestURI:" + request.getRequestURL());//该方法用于获取请求行中的资源名称部分即位于 URL 的主机和端门之后、参数部分之前的部分
            //  System.out.println("getQueryString:" + request.getQueryString());//该方法用于获取请求行中的参数部分，也就是资源路径后问号（？）以后的所有内容
            //  System.out.println("getContextPath:" + request.getContextPath());//该方法用于获取请求 URL 中属于 Web 应用程序的路径，这个路径以 / 开头，表示相对于整个 Web 站点的根目录，路径结尾不含 /。如果请求 URL 属于 Web 站点的根目录，那么返回结果为空字符串（""）
            //  System.out.println("getServletPath:" + request.getServletPath());//该方法用于获取 Servlet 的名称或 Servlet 所映射的路径
            //  System.out.println("getRemoteAddr : " + request.getRemoteAddr());//该方法用于获取请求客户端的 IP 地址，其格式类似于 192.168.0.3
            //   System.out.println("getRemoteHost : " + request.getRemoteHost());//该方法用于获取请求客户端的完整主机名，其格式类似于 pcl.mengma.com。需要注意的是，如果无法解析出客户机的完整主机名，那么该方法将会返回客户端的 IP 地址
            //  System.out.println("getRemotePort : " + request.getRemotePort());//该方法用于获取请求客户端网络连接的端口号
            //   System.out.println("getLocalAddr : " + request.getLocalAddr());//该方法用于获取 Web 服务器上接收当前请求网络连接的 IP 地址
            //   System.out.println("getLocalName : " + request.getLocalName());//该方法用于获取 Web 服务器上接收当前网络连接 IP 所对应的主机名
            //   System.out.println("getLocalPort : " + request.getLocalPort());//该方法用于获取 Web 服务器上接收当前网络连接的端口号
            //   System. out.println("getServerName : " + request.getServerName());//该方法用于获取当前请求所指向的主机名，即 HTTP 请求消息中 Host 头字段所对应的主机名部分
            //   System.out.println("getServerPort : " + request.getServerPort());//该方法用于获取当前请求所连接的服务器端口号，即 HTTP 请求消息中 Host 头字段所对应的端口号部分
            //   System.out.println("getRequestURL : " + request.getRequestURL());//该方法用于获取客户端发出请求时的完整 URL，包括协议、服务器名、端口号、 资源路径等信息，但不包括后面的査询参数部分。注意，getRequcstURL() 方法返冋的结果是 StringBuffer 类型，而不是 String 类型，这样更便于对结果进行修改


            InputStream inputStream = request.getInputStream();// 获取数据输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8")); // 将获取的输入流数据放进缓冲流
            StringBuffer sb = new StringBuffer("");// 定义一个空字符串缓冲容器
            String temp;// 定义字符串变量，用来比较
            while ((temp = br.readLine()) != null) {// br.readLine()会读取缓冲流里面的数据，并且复制给字符串变量temp，直到读完所有数据才会返回空，跳出循环
                sb.append(temp);// 没有跳出循环前，在上面定义的空字符串缓冲容器中不断将temp的值追加到容器sb的末尾，数据是连续性追加的
            }
            br.close();// 关闭流
            String acceptjson = sb.toString(); // 将字符串缓冲容器里的数据转换成普通字符串(json格式字符串)
            JSONObject json = JSONObject.fromObject(acceptjson);// 将上面的(json格式)字符串转为json对象

            System.out.println(json);//尝试打印上面的json对象

            User user = (User) JSONObject.toBean(json, User.class); // 将json对象转为java对象
            // System.out.println(user.getUserName());//测试转换来的java对象是否为空
            // 此方法只能把请求的json数据转换成User对象，调用此方法后还需要调用方法内的user对象的属性和方法
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 封装响应返回单个json对象的方法(参数是json对象)
     */
    public static void returnJsonObject(JSONObject json, HttpServletResponse response) {
        try {

            response.setContentType("application/json;charset=utf-8");// 指定返回的格式为JSON格式
            response.setCharacterEncoding("UTF-8");// setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
            String jsonStr = json.toString();// 将json对象转成字符串
            PrintWriter out = null;// 创建写者对象，将会写出物理json文件
            out = response.getWriter();
            out.write(jsonStr);
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 封装响应返回json数组对象的方法(参数是json数组)
     */
    public static void returnJsonArray(JSONArray jsonArray, HttpServletResponse response) {
        try {

            // 遍历json数组
            // for(int i=0;i<jsonArray.size();i++) {
            // System.out.println(ja.getJSONObject(i).get("id"));
            // }

            response.setContentType("application/json;charset=utf-8");// 指定返回的格式为JSON格式
            response.setCharacterEncoding("UTF-8");// setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
            String jsonStr = jsonArray.toString();// 将json对象转成字符串
            PrintWriter out = null;// 创建写者对象，将会写出物理json文件
            out = response.getWriter();
            out.write(jsonStr);
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 封装一个响应Boolean状态值的方法
     */

    public static void ErrMessageResponse(String status, HttpServletResponse response) {
        try {

            //JSONObject json = JSONObject.fromObject(status);//把传入的字符串进行转换，换成json对象
            response.setContentType("application/json;charset=utf-8");// 指定返回的格式为JSON格式
            response.setCharacterEncoding("UTF-8");// setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
            //String jsonStr = json.toString();// 将json对象转成字符串
            System.out.println(status);
            PrintWriter out = null;// 创建写者对象，将会写出物理json文件
            out = response.getWriter();
            out.write(status);
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}
