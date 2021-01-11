package cn.BSProject.Dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.BSProject.Entity.Teacher;
import cn.BSProject.Entity.User;
import cn.BSProject.utils.JdbcUtil;

public class AdminDao {

    //addOneUser/addOneStudent/addOneTeacher/addOneAdmin/deleteRole/
    // ----------------------------------------------------------------------------------------------------------


    // 类内全局变量字段\

    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
    // 方法排名 addOneUser/addOneStudent/addOneTeacher/addOneAdmin
    //deleteRole/deleteUser/deleteUser/findStudentByName/
    //findStudentByStudentId/findTeacherByName/findAdminByName
    //updateTeacher/updateStudent/findAllTeachers

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 管理员 添加一个一个账号，带三个参数，账号的id（通过学号或工号的截取获得），账号（学号或工号），身份类型（对应表名）
     */
    public int addOneUser(int id, String username, String role) throws SQLException {
        String sql = "insert into user (id,username,role) values (?,?,?)";
        Object[] params = {id, username, role};
        return runner.update(sql, params);// 执行sql语句，尝试添加学生对象到数据库

    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 管理员 添加一个一个学生
     */
    public int addOneStudent(String studentid, String name, String gender, int classid, int uid)
            throws SQLException {
        String sql = "insert into student (studentid,name,gender,classid,uid) values (?,?,?,?,?)";
        Object[] params = {studentid, name, gender, classid, uid};
        return runner.update(sql, params);// 执行sql语句，尝试添加学生对象到数据库,返回值是int类型s
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 管理员 添加一个一个老师
     */
    public int addOneTeacher(String teacherid, String name, String gender, String telephone, int uid)
            throws SQLException {
        String sql = "insert into teacher (teacherid,name,gender,telephone,uid) values (?,?,?,?,?)";
        Object[] params = {teacherid, name, gender, telephone, uid};
        return runner.update(sql, params);// 执行sql语句，尝试添加学生对象到数据库,返回值是int类型s
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 管理员 添加一个一个管理员账号
     */
    public int addOneAdmin(String adminid, String name, String gender, int age, String telephone, int uid)
            throws SQLException {
        String tel = telephone;
        System.out.println(tel);
        String sql = "insert into admin (adminid,name,gender,age,telephone,uid) values (?,?,?,?,?,?)";
        Object[] params = {adminid, name, gender, age, telephone, uid};
        return runner.update(sql, params);// 执行sql语句，尝试添加学生对象到数据库,返回值是int类型s
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 管理员 删除一个用户对象 一个用户对象包括账号表user和相信信息中的数据（student/teacher） 通过uid作为参数进行删除
     */
    // 第一步删除角色表中数据，并返回执行结果状态值
    public int deleteRole(String role, int uid) throws SQLException {
        String sql = "delete from " + role + " where uid = ?";// 删除用户信息的语句
        int params = uid;
        return runner.update(sql, params);

    }

    // 第二步删除账号表中数据，并返回执行结果状态值
    public int deleteUser(String role, int uid) throws SQLException {

        String sql = "delete from user where user.id = ?";
        int id = uid;
        return runner.update(sql, id);

    }

    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 根据姓名查询学生用户信息 返回值是集合 防止重名
     */
    public List<User> findStudentByName(String name) throws SQLException {

        String params = name;
        //模糊查询             SELECT studentid,name from student WHERE name LIKE '%谢文东%';
        String stusql = "select * from student s,user u where name = ? and s.uid = u.id";
        return runner.query(stusql, new BeanListHandler<User>(User.class), params);// 返回值是student集合

    }

    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 根据学生学号查询学生用户信息 返回值是对象student
     */
    public User findStudentByStudentId(String studentid) throws SQLException {

        String params = studentid;
        String sql = "select * from student s,user u where studentid = ? and s.uid = u.id";
        return runner.query(sql, new BeanHandler<User>(User.class), params);// 返回值是user

    }

    /**
     * 根据姓名查询老师用户信息 返回值是集合 防止重名
     */
    public List<User> findTeacherByName(String name) throws SQLException {

        String params = name;
        String teasql = "select * from teacher t,user u where name = ? and t.uid = u.id";
        return runner.query(teasql, new BeanListHandler<User>(User.class), params);// 返回值是student集合
    }

    /**
     * 根据姓名查询管理员用户信息 返回值是集合 防止重名
     */
    public List<User> findAdminByName(String name) throws SQLException {

        String params = name;
        String teasql = "select * from admin a,user u where name = ? and a.uid = u.id";
        return runner.query(teasql, new BeanListHandler<User>(User.class), params);// 返回值是student集合
    }

    // ok
    // ----------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 修改数据模块 管理员信息不能通过这里改，只能用户自登录自己修改
     */
    /**
     * 修改老师信息
     */

    public int updateTeacher(String name, String teacherid, String gender, String telephone, int uid)
            throws SQLException {
        String sql = " update teacher set teacherid = ? , name = '" + name + "' , gender = '" + gender + "' , telephone = ?  where uid = ?";
        Object[] params = {teacherid, telephone, uid};
        int flag = runner.update(sql, params);
        System.out.println("更新数据库语句的执行结果：" + flag);
        return flag;
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 修改学生信息
     */

    public int updateStudent(String name, String studentid, String gender, int classid, int uid)
            throws SQLException {
        String sql = " update student set studentid = ? , name = '" + name + "' , gender = '" + gender + "' ,classid = ? where uid = ?";
        Object[] params = {studentid, classid, uid};
        int flag = runner.update(sql, params);
        System.out.println("更新数据库语句的执行结果：" + flag);
        return flag;
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 查找所有老师
     */
    public List<Teacher> findAllTeachers()
            throws SQLException {
        String sql = "select * from teacher";
        return runner.query(sql, new BeanListHandler<Teacher>(Teacher.class));
    }


    // ----------------------------------------------------------------------------------------------------------------------------------------


    /**
     * 根据老师工号查询老师用户信息 返回值是对象teacher
     */
    public User findTeacherByTeacherId(String teacherid) throws SQLException {
        String sql = "select * from teacher t,user u where teacherid = '" + teacherid + "' and t.uid = u.id";
        return runner.query(sql, new BeanHandler<User>(User.class));// 返回值是user

    }


    // ----------------------------------------------------------------------------------------------------------------------------------------


    /**
     * 重置密码
     */
    public int resetPassword(int uid, String password) throws SQLException {
        String sql = "update user set password = '" + password + "' where id = ?";
        Object params = uid;
        return runner.update(sql, params);
    }


    /*
     * 通过账号查询账号是否存在
     */
    public User findUserByUsername(String username) throws SQLException {
        String sql = "select * from user where username = ?";
        String params = username;
        return runner.query(sql, new BeanHandler<User>(User.class), params);
    }
}
