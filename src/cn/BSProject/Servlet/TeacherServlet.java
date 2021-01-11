package cn.BSProject.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BSProject.Entity.Question;
import cn.BSProject.Entity.User;
import cn.BSProject.Service.TeacherService;
import cn.BSProject.utils.MyUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/Teacher")
public class TeacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //
    TeacherService teacherService = new TeacherService();
    User user = new User();
    Question question = new Question();

    // 执行成功与失败的返回值字符串
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
            case "updateStudent":
                this.updateStudent(request, response);
                break;
            case "findStudentByClassID":
                this.findStudentByClassID(request, response);
                break;
            case "findStudentByName":
                this.findStudentByName(request, response);
                break;
            case "findStudentByStudentId":
                this.findStudentByStudentId(request, response);
                break;
            case "findAllClass":
                this.findAllClass(request, response);
                break;
            case "addStudent":
                this.addStudent(request, response);
                break;
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    // ok

    /**
     * 老师 修改学生信息 返回值是状态值 参数 name,studentid,gender,age,score,uid
     */
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean flag = teacherService.updateStudent(user.getName(), user.getStudentid(), user.getGender(), user.getClassid(), user.getUid());// 调用service层方法
            if (flag) {

                // JSONObject json = JSONObject.fromObject("{'status':'success'}");
                // MyUtil.returnJsonObject(json, response);
                // System.out.println(json);
                MyUtil.ErrMessageResponse(successstatus, response);
            } else {
                // JSONObject json = JSONObject.fromObject("{'status':'failure'}");
                // MyUtil.returnJsonObject(json, response);
                // System.out.println(json);
                // System.out.println("修改失败，请联系管理员");
                MyUtil.ErrMessageResponse(failurestatus, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            MyUtil.ErrMessageResponse(failurestatus, response);
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    // ok

    /**
     * 通过班级编号查询该班级所有学生 返回值是集合 参数 classid
     */
    private void findStudentByClassID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONArray jsonArray = null;
        int classid = user.getClassid();
        //System.out.println(classid);//打印传递进来的查询班级编号
        // json请求查询班级所欲学生，返回值是jsonjsonArray
        try {
            jsonArray = JSONArray.fromObject(teacherService.findStudentByClassID(classid));// 对象集合转json数组JSONArray
            // for (int i = 0;i<jsonArray.size();i++) {
            // System.out.println(jsonArray.get(i));//将封装好的jsonArray的元素一一打印出来
            // }
            if (jsonArray != null) {
                MyUtil.returnJsonArray(jsonArray, response);// 调用参数是json数组的响应方法，响应数据写出json数组，里面是一个个json对象
            } else {
                MyUtil.returnJsonArray(jsonArray, response);
            }

            //System.out.println(jsonArray);//打印查询结果

        } catch (Exception e) {
            e.printStackTrace();

            MyUtil.returnJsonArray(jsonArray, response);//响应空jsonArray
        }

    }

    // ----------------------------------------------------------------------------------------------------------

    // ok

    /**
     * 通过学生名查找学生 返回值是集合，，防止重名 参数 name
     */

    private void findStudentByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(user.getName());
            JSONArray jsonArray = JSONArray.fromObject(teacherService.findStudentByName(user.getName()));// 接受集合并且直接转换为jsonArray
            MyUtil.returnJsonArray(jsonArray, response);// 调用参数是json数组的响应方法，响应数据写出json数组，里面是一个个json对象
            System.out.println(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 通过学生学号查找学生 返回值是user 参数 studentid
     */

    private void findStudentByStudentId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("学生id:" + user.getStudentid());
            JSONObject json = JSONObject.fromObject(teacherService.findStudentByStudentId(user.getStudentid()));// 接受集合并且直接转换为jsonArray
            MyUtil.returnJsonObject(json, response);// 调用参数是json数组的响应方法，响应数据写出json数组，里面是一个个json对象
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------------------------------------------

    /**
     * 查找所有班级
     */

    private void findAllClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            JSONArray jsonArray = JSONArray.fromObject(teacherService.findAllClass());
            MyUtil.returnJsonArray(jsonArray, response);
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加一个学生
     */
    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean flag = teacherService.addStudent(user);
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


}
