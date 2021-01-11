package cn.BSProject.Dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.BSProject.utils.JdbcUtil;

public class UserDao {

    // 类内全局变量字段
    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 用户登录
     */
    public Map<String, Object> login(String username, String password) throws SQLException {

        String sql = "select * from user where username = ? and password = ?";
        Object[] params = {username, password};
        // 执行sql语句，如果查询到结果一定是一条语句，并把这条语句封装成User对象
        // 如果查询不到返回null
        return runner.query(sql, new MapHandler(), params);
    }


    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 通过账号编号uid查询该账号所属角色详细信息
     * MapHandler()将一条记录封装成一个map对象，key(String)表示字段名，value(Object)表示字段值
     */
    public Map<String, Object> findRoleInfo(String role, int uid) throws SQLException {
        String sql = "select * from " + role + " r ,user u where r.uid = u.id and uid = ?";// 第一个参数是表名（身份），第二个是uid，两个都在user表中存在
        Object[] params = {uid};
        // 返回值是类似json对象的键值对对象：key(String)表示字段名，value(Object)表示字段值
        return runner.query(sql, new MapHandler(), params);
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 修改密码
     */
//	public Map<String, Object> changePassword(String username, String newpassword) throws SQLException {
//
//		String sql = " update user set password = ? where username = ?";
//		Object[] params = { newpassword, username };
//		runner.update(sql, params);//执行修改数据库的语句
//		return login(username, newpassword);//将修改密码后的对象返回
//	}

    // ----------------------------------------------------------------------------------------------------------------------------------------


    /**
     * 修改密码
     */
    public int changePassword(String username, String newpassword) throws SQLException {

        String sql = " update user set password = ? where username = ?";
        Object[] params = {newpassword, username};
        return runner.update(sql, params);//执行修改数据库的语句
    }

    /**
     * 记录学生分数
     */
    public int setStudentScore(String studentid, double score) throws SQLException {
        String sql = "update student set score = ? where studentid = " + studentid;
        System.out.println(studentid);
        System.out.println(score);
        Object[] params = {
                score
        };
        return runner.update(sql, params);
    }

}
