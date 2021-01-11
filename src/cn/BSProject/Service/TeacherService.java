package cn.BSProject.Service;

import java.util.ArrayList;
import java.util.List;

import cn.BSProject.Dao.AdminDao;
import cn.BSProject.Dao.TeacherDao;
import cn.BSProject.Entity.Classes;
import cn.BSProject.Entity.Student;
import cn.BSProject.Entity.User;
import net.sf.json.JSONObject;

public class TeacherService {

    TeacherDao teacherDao = new TeacherDao();

    AdminDao adminDao = new AdminDao();

    AdminService adminService = new AdminService();

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 老师 修改学生信息
     */

    public boolean updateStudent(String name, String studentid, String gender, int classid, int uid) throws Exception {

        int flag = adminDao.updateStudent(name, studentid, gender, classid, uid);
        return flag > 0 ? true : false;// 如果dao层返回的之是1>0说明修改成功
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 通过班级编号classid查询该班级所有学生 封装ok返回值List<Student>集合
     */
    public List<Student> findStudentByClassID(int classid) throws Exception {
        List<Student> studentlist = teacherDao.findStudentByClassID(classid);


        //对返回的集合先进行重新排序后再次返回个servlet层
        //-------------------------------------------------------------------

        List<Student> newstudentlist = new ArrayList<Student>();//定义一个新的有序集合，对返回的集合进行排序
        for (int i = 0; i < studentlist.size(); i++) {
            JSONObject stu = JSONObject.fromObject(studentlist.get(i));//获取返回的集合的每一个元素并转成json对象
            Student student = (Student) JSONObject.toBean(stu, Student.class);//在吧json对象转成Javabean----student
            newstudentlist.add(student);
            newstudentlist.get(i).setOrdnum(i + 1);
            //System.out.println(newstudentlist.get(i).getOrdnum());//打印集合元素的序号
            //System.out.println(newstudentlist.get(i).getName());//打印元素的姓名属性
        }
        return newstudentlist;//返回数据给servlet层

        //------------------------------------------------------------------

        //原始返回数据代码
        //return teacherDao.findStudentByClassID(classid);
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 通过学生姓名查询学生信息 返回值是集合 防止学生重名
     */
    public List<User> findStudentByName(String name) throws Exception {
        return adminDao.findStudentByName(name);// 返回查询结果集
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 通过学生学号查询学生信息 返回值是user
     */
    public User findStudentByStudentId(String studentid) throws Exception {
        return adminDao.findStudentByStudentId(studentid);// 返回查询结果集
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 查找所有班级
     */

    public List<Classes> findAllClass() throws Exception {
        return teacherDao.findAllClass();
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 添加一个学生
     */
    public boolean addStudent(User user) throws Exception {
        int uid = 0;
        String studentid = user.getStudentid();
        //System.out.println(studentid);
        int classid = user.getClassid();
        if (adminDao.findStudentByStudentId(studentid) == null) {// 查询学生表是否已经存在这个学生

            System.out.println("学生不存在，可以尝试添加");
            //判断传入的studentid和班级id是否相等
            if (classid == Integer.parseInt(studentid.substring(4, 6))) {
                //System.out.println("班级id截取："+Integer.parseInt(studentid.substring(4, 6)));
                String name = user.getName();
                String gender = user.getGender();

                //int age = user.getAge();
                uid = Integer.parseInt(studentid);
                adminDao.addOneStudent(studentid, name, gender, classid, uid);
                System.out.println("学生表记录添加成功，继续执行添加user表");
                if (adminDao.findUserByUsername(studentid) == null) {
                    int flag = adminDao.addOneUser(Integer.parseInt(studentid), studentid, "student");// 执行添加user表记录的方法并返回int型状态值
                    return flag > 0 ? true : false;
                } else {

                    return adminService.resetPassword(Integer.parseInt(studentid));
                }
            } else {
                System.out.println("传入的班级id和studentid相关数字有误！！");
                //adminDao.deleteRole("student", uid);//
                return false;
            }

        } else {
            System.out.println("学生已存在，添加失败");
            return false;
        }

    }

    /**
     * 老师 添加一个一个学生
     */
//	private void addOneStudent(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			//获取要添加的学生的基本信息
//			String studentid = user.getStudentid();
//			String name = user.getName();
//			String gender = user.getGender();
//			int age = user.getAge();
//			int classid = user.getClassid();
//			int uid = user.getUid();
//			
//			
//			
//			//调用方法进行添加学生List<Student> map= userService.addOneStudent(studentid,name,gender,age,classid);
//			//调用方法执行进行添加操作,并返回一个查询该班级所有学生的结果集合，直接转换成jsonArray数组
//			JSONArray jsonArray=JSONArray.fromObject(userService.addOneStudent(studentid,name,gender,age,classid,uid));
//			this.returnJsonArray(jsonArray, response);//将更新后的班级所有学生查询结果响应给前端
//			System.out.println(jsonArray);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

    // ----------------------------------------------------------------------------------------------------------

}
