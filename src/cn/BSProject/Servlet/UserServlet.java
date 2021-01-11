package cn.BSProject.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BSProject.Entity.Student;
import cn.BSProject.Entity.User;
import cn.BSProject.Service.UserService;
import cn.BSProject.utils.MyUtil;
import net.sf.json.JSONObject;

/**
 * 普通用户操作模块
 *
 * @author Lld
 */
@SuppressWarnings("serial")
@WebServlet("/User")
public class UserServlet extends HttpServlet {

    // 公有字段属性
    User user = new User();// 用户实体类型对象
    UserService userService = new UserService();// 用户业务层对象
    Student student = new Student();// 学生实体类型对象

    //执行成功与失败的返回值字符串
    String successstatus = "{'status':'success'}";
    String failurestatus = "{'status':'failure'}";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request, response);// 当时get请求是调用dopost方法
    }

    /**
     * http请求分发器
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 普通表单请求用一下方法处理
        // this.getformmethod(request, response);

        // json格式数据请求采用getmethod方法处理请求
        user = MyUtil.getjsonmethod(request, response);
        String method = user.getMethod();// 获取json的method参数的值，根据这个值分发处理方法
        System.out.println(method);
        switch (method) {
            case "login":
                this.login(request, response);
                break;
            case "findRoleInfo":
                this.findRoleInfo(request, response);
                break;
            case "changePassword":
                this.changePassword(request, response);
                break;
            case "setStudentScore":
                this.setStudentScore(request, response);
                break;
        }
    }


    // ----------------------------------------------------------------------------------------------------------

    //ok

    /**
     * 账号登录     返回值是map
     */
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //user = MyUtil.getjsonmethod(request, response);
            // 表单请求时获取参数请求传递的
            // String username = request.getParameter("username");
            // String password = request.getParameter("password");

            // json请求时获取参数请求传递的
            String username = user.getUsername();
            String password = user.getPassword();
            System.out.println(username);
            System.out.println(password);

            // 无论是什么格式的请求，返回的数据一律封装成json对象

            //初步查询到用户名和密码信息
            Map<String, Object> map = userService.login(username, password);// 调用业务层方法，接收返回值map数据包
            JSONObject json = JSONObject.fromObject(map);// 将返回的map对象转换成json对象,返回的是user表的信息
            System.out.println(json);
            if (json != null) {
                //先将普通用户表的账号和密码等信息拿到，再取出role身份信息和uid账号编号，调用查询账号拥有者详细身份信息
                user = (User) JSONObject.toBean(json, User.class);//将建json对象转换为User对象
                String role = user.getRole();//拿到role身份信息
                System.out.println(role);
                int uid = user.getId();//拿到uid账号编号信息
                System.out.println(uid);
                map = userService.findRoleInfo(role, uid);//调用查询详细信息的方法，查询详细信息，返回值是map对象，将返回的map重新赋值给已经定义好的map
                json = JSONObject.fromObject(map);// 将更新数据后的map对象转换成json对象,返回的是user表和student（或teacher）表的合成信息
                //System.out.println(json);// 测试封装后的json对象是否为空
                // request.setAttribute("USER", json);//将封装好的json数据放置到request域中
                if (json != null) {
                    // HttpSession session = request.getSession();// 获取一个session
                    // session.setAttribute("USER", json);//
                    // 将封装好的json数据放置到session中，第一个是常量前端通过，第二个是变量
                    //System.out.println("登录成功");
                    // response.sendRedirect(request.getContextPath()+"/index.jsp");

                    // 调用响应数据为单个json对象的响应方法
///////////////////////////////response.setStatus(1);////////////////////////该方法用于设置 HTTP 响应消息的状态码，并生成响应状态行。由于响应状态行中的状态描述信息直接与状态码相关，而 HTTP 版本由服务器确定，因此，只要通过 setStatus（int status）方法设置了状态码，即可实现状态行的发送。需要注意的是，在正常情况下，Web 服务器会默认产生一个状态码为 200 的状态行。

                    MyUtil.returnJsonObject(json, response);
                    System.out.println(json);
                } else {
                    json = JSONObject.fromObject(failurestatus);
                    System.out.println(json);
                    MyUtil.returnJsonObject(json, response);
//					MessageUtil.forward("用户名或密码错误", request, response);
                    //MyUtil.returnJsonObject(json, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JSONObject json = null;
            JSONObject json = JSONObject.fromObject(failurestatus);
            System.out.println(json);
            MyUtil.returnJsonObject(json, response);
            //MyUtil.returnJsonObject(json, response);
//			MessageUtil.forward("登录异常,请联系管理员", request, response);
        }
    }

    // ----------------------------------------------------------------------------------------------------------


    private void returnJsonObject(JSONObject json, HttpServletResponse response) {
        // TODO Auto-generated method stub

    }

    /**
     * 通过账号编号uid查询该账号所属角色详细信息    返回值是user
     */
    private void findRoleInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //form表单请求获取参数
            // String role = request.getParameter("role");
            // int uid = Integer.parseInt(request.getParameter("uid"));

            //json请求获取参数
            String role = user.getRole();
            int uid = user.getUid();


            // 创建业务层对象并调用方法接收返回值，map对象
            Map<String, Object> map = userService.findRoleInfo(role, uid);

            // 将返回的map对象转换成json对象
            JSONObject json = JSONObject.fromObject(map);
            System.out.println(json);//尝试打印返回的数据

            //测试打印返回的用户所有信息
            //User user = (User) JSONObject.toBean(json, User.class);

            //调用响应方法返回数据
            MyUtil.returnJsonObject(json, response);


        } catch (Exception e) {
            e.printStackTrace();
            JSONObject json = null;
            MyUtil.returnJsonObject(json, response);//响应空json对象
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    //ok
    /**
     * 修改密码   返回值是    状态值
     */
//	private void changePassword(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		try {
//			//json请求获取参数
//			String username = user.getUsername();
//			String password = user.getPassword();
//			String newpassword = user.getNewpassword();
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println(newpassword);
//
//			Map<String,Object> map = userService.changePassword(username, password, newpassword);//调用业务层方法，接收map数据
//			JSONObject json = JSONObject.fromObject(map);// 将返回的map对象转换成json对象
//			 MyUtil.returnJsonObject(json, response);//调用响应方法发送数据
//			System.out.println(user.getPassword());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("密码修改失败，请联系管理员");
//		}
//
//	}

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 修改密码   返回值是    状态值
     */
    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //json请求获取参数
            String username = user.getUsername();
            String password = user.getPassword();
            String newpassword = user.getNewpassword();

            System.out.println(username);
            System.out.println(password);
            System.out.println(newpassword);

            boolean flage = userService.changePassword(username, password, newpassword);//调用业务层方法，接收map数据
            if (flage) {
                MyUtil.ErrMessageResponse(successstatus, response);
            } else {
                MyUtil.ErrMessageResponse(failurestatus, response);
            }
        } catch (Exception e) {
            e.printStackTrace();

            MyUtil.ErrMessageResponse(failurestatus, response);
            System.out.println("密码修改失败，请联系管理员");
        }

    }


    //登记学生分数     模拟考计分
    private void setStudentScore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentid = user.getStudentid();
        double score = user.getScore();
        try {
            boolean flag = userService.setStudentScore(studentid, score);
            if (flag) {
                MyUtil.ErrMessageResponse(successstatus, response);
            } else {
                MyUtil.ErrMessageResponse(failurestatus, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MyUtil.ErrMessageResponse(failurestatus, response);
        }
    }

}
