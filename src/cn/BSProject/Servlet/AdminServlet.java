package cn.BSProject.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BSProject.Entity.User;
import cn.BSProject.Service.AdminService;
import cn.BSProject.Service.TeacherService;
import cn.BSProject.utils.MyUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {

    //addOneUser/deleteUser/findUserByName/updateUser/findAllTeachers/updateTeacher
    // ------------------------------------------------------------------------------------------------------------

    AdminService adminService = new AdminService();
    User user = new User();
    TeacherService teacherService = new TeacherService();

    //执行成功与失败的返回值字符串
    String successstatus = "{'status':'success'}";
    String failurestatus = "{'status':'failure'}";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        user = MyUtil.getjsonmethod(request, response);
        String method = user.getMethod();// 获取json的method参数的值，根据这个值分发处理方法
        switch (method) {

//		case "addOneUser":
//			this.addOneUser(request, response);
//			break;
            case "deleteUser":
                this.deleteUser(request, response);
                break;
            case "findUserByName":
                this.findUserByName(request, response);
                break;
//		case "updateUser":
//			this.updateUser(request, response);
//			break;
            case "findAllTeachers":
                this.findAllTeachers(request, response);
                break;
            case "updateTeacher":
                this.updateTeacher(request, response);
                break;
            case "findTeacherByTeacherId":
                this.findTeacherByTeacherId(request, response);
                break;
            case "resetPassword":
                this.resetPassword(request, response);
                break;
            case "addTeacher":
                this.addTeacher(request, response);
                break;

        }
    }


    // ------------------------------------------------------------------------------------------------------------


    //ok
//	/**
//	 * 管理员 添加一个账号包括姓名信息 执行王立刻查询刚刚添加的姓名的用户    参数    role  name   studentid/teacherid/adminid   gender  age    
//	 *  学生要有classid   老师和管理员要有 telephone
//	 */
//	private void addOneUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			 System.out.println(user.getRole());
//			if(adminService.addOneUser(user)) {    // 执行service层的方法  如果true则添加成功否则添加失败
//				//this.findUserByName(request, response);
//				MyUtil.ErrMessageResponse(successstatus, response);//添加成功
//			}
//			else {
//				MyUtil.ErrMessageResponse(failurestatus, response);//添加失败
//			}

//			String name = user.getName();// 获取刚刚添加的账号主人的姓名
//			String role = user.getRole();
//			JSONArray jsonArray = JSONArray.fromObject(adminService.findUserByName(role, name));// 查询刚刚添加的用户
//			System.out.println(jsonArray);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


    //ok
    // ------------------------------------------------------------------------------------------------------------

    /**
     * 管理员 删除用户
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String role = user.getRole();
            int uid = user.getUid();
            boolean flag = adminService.deleteUser(role, uid);// 此方法也要传递的两个参数
            if (flag == true) {
                System.out.println("删除成功");
                //String status = "{'status':'success'}";
                //JSONObject json = JSONObject.fromObject(status);// 封装一个json对象储存处理结果的状态值
                //MyUtil.returnJsonObject(json, response);// 将json相应到前端
                MyUtil.ErrMessageResponse(successstatus, response);
                //System.out.println(json);
            } else {
                System.out.println("删除失败");
                MyUtil.ErrMessageResponse(failurestatus, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // ------------------------------------------------------------------------------------------------------------


    //ok

    /**
     * 根据姓名查询用户信息 返回值是集合 防止重名
     */
    private void findUserByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = user.getName();// 获取刚刚添加的账号主人的姓名
            JSONArray jsonArray = JSONArray.fromObject(adminService.findUserByName(name));
            System.out.println(jsonArray);
            MyUtil.returnJsonArray(jsonArray, response);//把查询结果响应给前端（请求方）
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------------------------------------------


    //ok
    /**
     * 修改用户信息s
     */
//	private void updateUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			boolean flag = adminService.updateUser(user);
//			if(flag) {
//				
//				//JSONObject json = JSONObject.fromObject(status);
//				//MyUtil.returnJsonObject(json, response);
//				MyUtil.ErrMessageResponse(successstatus, response);
//				//System.out.println(json);
//			}
//			else {
//				
//				//JSONObject json = JSONObject.fromObject(status);
//				//MyUtil.returnJsonObject(json, response);
//				MyUtil.ErrMessageResponse(failurestatus, response);
//				//System.out.println(json);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

    // ------------------------------------------------------------------------------------------------------------

    /**
     * 查找所有老师
     */
    private void findAllTeachers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            JSONArray jsonArray = JSONArray.fromObject(adminService.findAllTeachers());
            MyUtil.returnJsonArray(jsonArray, response);
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------------------------------------------

    /**
     * 修改老师信息
     */
    private void updateTeacher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            adminService.updateTeacher(user);
            MyUtil.ErrMessageResponse(successstatus, response);
        } catch (SQLException e) {
            e.printStackTrace();
            MyUtil.ErrMessageResponse(failurestatus, response);
        }
    }

    // ------------------------------------------------------------------------------------------------------------

    /**
     * 修改老师信息
     */
    private void findTeacherByTeacherId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String teacherid = user.getTeacherid();
        try {
            user = adminService.findTeacherByTeacherId(teacherid);
            JSONObject json = JSONObject.fromObject(user);
            System.out.println(json);
            MyUtil.returnJsonObject(json, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ------------------------------------------------------------------------------------------------------------


    /**
     * 重置密码
     */
    private void resetPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int uid = user.getUid();

            boolean flag = adminService.resetPassword(uid);
            if (flag) {
                MyUtil.ErrMessageResponse(successstatus, response);
            } else {
                MyUtil.ErrMessageResponse(failurestatus, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyUtil.ErrMessageResponse(failurestatus, response);
        }

    }


    private void addTeacher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean flag = adminService.addTeacher(user);
            if (flag) {
                System.out.println(flag);
                MyUtil.ErrMessageResponse(successstatus, response);

            } else {
                MyUtil.ErrMessageResponse(failurestatus, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyUtil.ErrMessageResponse(failurestatus, response);
        }
    }
}
