package cn.BSProject.Service;

import java.sql.SQLException;
import java.util.List;


import cn.BSProject.Dao.AdminDao;
import cn.BSProject.Entity.Teacher;
import cn.BSProject.Entity.User;
import cn.BSProject.utils.Md5Util;

public class AdminService {

    //addOneUser/deleteUser/findUserByName/updateUser/
    // ------------------------------------------------------------------------------------------------------------


    AdminDao adminDao = new AdminDao();

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 添加一个账号包括姓名信息
     */
//	public boolean addOneUser(User user) throws Exception {
//		String role = user.getRole();// 获取账号类型
//		String name = user.getName();// 获取账号主人姓名
//		String gender = user.getGender();// 获取性别
//		int age = user.getAge();// 获取年龄
//		int uid = 0;
//		int classid = 0;
//		String telephone = user.getTelephone();// 获取账号主人的练习电话
//
//		System.out.println(role);
//		System.out.println(name);
//		System.out.println(gender);
//		System.out.println(age);
//		System.out.println(telephone);
//
//		// 判断该有的信息都不能为空才能继续执行
//		if (role != null && name != null && gender != null && age >= 0) {
//			System.out.println(role);
//			if (role.equals("student")) {// 如果是添加学生账号,获取要添加的学生的基本信息
//
//				String studentid = user.getStudentid();// 获取学生格式的id
//				uid = Integer.parseInt(studentid);// 将获取学生格式的id进行字符串的截取，截取结果用作账号的id和学生表的uid
//				classid = user.getClassid();// 获取班级id
//				System.out.println(classid);
//				int claid = Integer.parseInt(studentid.substring(4, 6)) ;
//				System.out.println(claid);
//				if (classid != 0  && classid == claid) {// 带上有效班级id才能让学生添加成功  并且班级id与学号中包含的班级id相等才进行添加操作
//					System.out.println(uid);
//					int stustatus = adminDao.addOneStudent(studentid, name, gender,  classid, uid);// 先执行插入学生表的操作并接受dao层添加学生方法返回的int型参数
//					System.out.println(stustatus);
//
//					int userstatus = adminDao.addOneUser(uid, studentid, role);// 继续执行dao层方法将带有以上用户信息的账号添加到数据库，并接受返回参数
//					System.out.println(userstatus);
//					return true;
//				} else {
//					System.out.println("班级id无效");
//					return false;
//				}
//			}
//			if (role.equals("teacher")) {
//				String teacherid = user.getTeacherid();
//				
//				String teacher = teacherid.substring(0, 7);
//				
//				if(teacher.equals("teacher"))
//				{
//					uid = Integer.parseInt(teacherid.substring(7));
//					if(uid >=1001 && uid <= 9999) {        //限制教师的id在1001到9999以内
//						// System.out.println(uid);//尝试打印截取到的老师职工id尾号
//						int teastatus = adminDao.addOneTeacher(teacherid, name, gender, telephone, uid);// 执行添加老师用户的操作，并返回int类型参数
//						System.out.println("添加老师的操作状态：" + teastatus);
//						int userstatus = adminDao.addOneUser(uid, teacherid, role);// 继续执行dao层方法将带有以上用户信息的账号添加到数据库，并接受返回参数
//						System.out.println("插入账号的操作状态：" + userstatus);
//						return true;
//					}
//					else {
//						System.out.println("教师id超出范围，管理员编号在1001~9999之间");
//						return false;
//					}
//					
//				}
//				else {
//					System.out.println("老师注册id格式错误，必须以teacher单词开头");
//					return false;
//				}
//				
//			}
//			if (role.equals("admin")) {
//				String adminid = user.getAdminid();
//				System.out.println(adminid);
//				
//				String admin = adminid.substring(0, 5);
//				System.out.println(admin);
//				if(admin.equals("admin")) {
//					uid = Integer.parseInt(adminid.substring(5));
//					if(uid>0 && uid <= 999) {          //限制管理员的id在1000以内
//						// 执行添加管理员的方法并返回处理结果 int类型数据
//						int adminstatus = adminDao.addOneAdmin(adminid, name, gender, age, telephone, uid);
//						System.out.println("插入管理员的操作状态：" + adminstatus);
//						int userstatus = adminDao.addOneUser(uid, adminid, role);// 继续执行dao层方法将带有以上用户信息的账号添加到数据库，并接受返回参数
//						System.out.println("插入账号的操作状态：" + userstatus);
//						return true;
//					}
//					else {
//						System.out.println("管理员id超出范围，管理员编号在1~999之间");
//						return false;
//					}
//					
//				}
//				else {
//					System.out.println("管理员注册id格式错误，必须以admin单词开头");
//					return false;
//				}
//				
//				
//			}
//
//			// 二层
//			else {
//				System.out.println("传入的账号类型参数非法！！！");
//				return false;
//			}
//		}
//		// 最外层
//		else {
//			System.out.println("传入的参数有误!!");
//			return false;
//		}
//
//	}

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 管理员 删除一个用户对象 一个用户对象包括账号表user和相信信息中的数据（student/teacher） 通过uid作为参数进行删除
     */
    public boolean deleteUser(String role, int uid) throws Exception {

        if (role != "admin") {

            if (adminDao.deleteRole(role, uid) > 0) {
                int flag = adminDao.deleteUser(role, uid);// 执行删除用户信息操作
                return flag > 0 ? true : false;
            } else {
                System.out.println("删除失败");
                return false;
            }

        } else {
            System.out.println("不能删除管理员或用户类型错误");
            return false;
        }

    }

    // ------------------------------------------------------------------------------------------------------------


    /**
     * 根据姓名查询用户信息 返回值是集合 防止重名
     */
    public List<User> findUserByName(String name) throws Exception {
        List<User> users = adminDao.findAdminByName(name);
        List<User> users2 = adminDao.findTeacherByName(name);
        List<User> users3 = adminDao.findStudentByName(name);
        for (int i = 0; i < users2.size(); i++) {
            if (users2.get(i) != null) {
                users.add(users2.get(i));

            }

        }
        for (int j = 0; j < users3.size(); j++) {
            if (users3.get(j) != null) {
                users.add(users3.get(j));
            }
        }
        return users;

    }

    // ------------------------------------------------------------------------------------------------------------


    /**
     * 修改用户信息
     */
//	public boolean updateUser(User user) throws Exception {
//		
//		String role = user.getRole();
//		String name = user.getName();
//		String gender = user.getGender();
//		int age = user.getAge();
//		int uid = user.getUid();
//		System.out.println(role);
//		
//		if(role.equals("student")) {
//			String studentid = user.getStudentid();
//			int classid = user.getClassid();
//			int flag = adminDao.updateStudent(name, studentid, gender, classid, uid);
//			return flag > 0 ? true : false;// 如果dao层返回的之是1>0说明修改成功
//		}
//		if(role.equals("teacher")) {
//			String teacherid = user.getTeacherid();
//			String telephone = user.getTelephone();
//			int flag =adminDao.updateTeacher(name, teacherid, gender, age, telephone, uid);
//			return flag > 0 ? true : false;// 如果dao层返回的之是1>0说明修改成功
//		}
//		else {
//			return false;
//		}
//		
//	} 


    // ------------------------------------------------------------------------------------------------------------

    /**
     * 查找所有老师
     */
    public List<Teacher> findAllTeachers()
            throws Exception {
        return adminDao.findAllTeachers();

    }

    // ------------------------------------------------------------------------------------------------------------

    /**
     * 修改老师信息
     */
    public boolean updateTeacher(User user)
            throws SQLException {

        String name = user.getName();
        String teacherid = user.getTeacherid();
        String gender = user.getGender();
        String telephone = user.getTelephone();
        int uid = user.getUid();
        int flag = adminDao.updateTeacher(name, teacherid, gender, telephone, uid);
        return flag > 0 ? true : false;
    }

    /**
     * 根据老师工号查询老师用户信息 返回值是对象teacher
     */
    public User findTeacherByTeacherId(String teacherid) throws Exception {
        return adminDao.findTeacherByTeacherId(teacherid);


    }

    /**
     * 重置密码
     *
     * @throws Exception
     */
    public boolean resetPassword(int uid) throws Exception {

        String password = "123456";
        String passwordMd5 = Md5Util.encodeByMd5(password);
        //重置的密码一律是123456加密后的密码
        int flag = adminDao.resetPassword(uid, passwordMd5);
        return flag > 0 ? true : false;


    }


    /**
     * 添加一个老师
     */
    public boolean addTeacher(User user) throws Exception {
        int uid = 0;
        String teacherid = user.getTeacherid();
        //查询这个老师是否已经存在
        if (adminDao.findTeacherByTeacherId(teacherid) == null) {
            System.out.println("老师不存在，可以尝试添加");
            String name = user.getName();
            String gender = user.getGender();
            //int age = user.getAge();
            String telephone = user.getTelephone();
            uid = Integer.parseInt(teacherid.substring(7));
            adminDao.addOneTeacher(teacherid, name, gender, telephone, uid);//执行添加老师表记录的操作
            System.out.println(adminDao.findTeacherByTeacherId(teacherid));//执行完添加操作后立马查询并打印是否添加老师表成功

            //执行完添加老师表记录的操作后，查询职工id是否已在user表中存在
            if (adminDao.findTeacherByTeacherId(teacherid) == null) {
                int flag = adminDao.addOneUser(uid, teacherid, "teacher");//执行添加user表的操作
                return flag > 0 ? true : false;
            } else {
                adminDao.deleteRole("teacher", uid);
                return this.resetPassword(uid);
            }

        } else {
            return false;
        }


    }

}
