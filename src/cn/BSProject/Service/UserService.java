package cn.BSProject.Service;

import java.sql.SQLException;
import java.util.Map;

import cn.BSProject.Dao.UserDao;
import cn.BSProject.Entity.User;
import cn.BSProject.utils.Md5Util;

public class UserService {

    private UserDao userDao = new UserDao();
    User user = new User();

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 账号登录
     */
    public Map<String, Object> login(String username, String password) throws Exception {

        String passwordMd5 = Md5Util.encodeByMd5(password);// 将原始密码进行加密
        System.out.println("加密后的密码：" + passwordMd5);
        //调用dao层方法
        Map<String, Object> map = userDao.login(username, passwordMd5);
        return map;
    }

    // ----------------------------------------------------------------------------------------------------------


    /**
     * 通过账号编号uid查询该账号所属角色详细信息 封装ok返回值map
     */
    public Map<String, Object> findRoleInfo(String role, int uid) throws Exception {
        // 创建map对象储存调用到层方法的返回值map对象
        Map<String, Object> map = userDao.findRoleInfo(role, uid);

        // User user = new User();//创建user对象
        // BeanUtils.populate(user, map);//将map对象中键名与user对象的属性名一致的值赋值给user对象，封装属性
        return map;// 把对象返回给servlet层
    }


    // ----------------------------------------------------------------------------------------------------------


    /**
     * 修改密码
     */
//	public Map<String, Object> changePassword(String username, String password, String newpassword) throws Exception {
//		// 将原始旧密码进行加密
//		String passwordMd5 = Md5Util.encodeByMd5(password);
//
//		// 将新密码同样进行加密
//		String newpasswordMd5 = Md5Util.encodeByMd5(newpassword);
//
//		// 调用dao层登录模块方法查找账号和密码对应的用户，验证用户登录，返回没修改密码的用户
//		Map<String, Object> map = userDao.login(username, passwordMd5);
//
//		if (map != null) {
//			// 调用更新用户密码的方法 返回值是Map<String,Object> map对象
//			return userDao.changePassword(username, newpasswordMd5);
//		} else {
//			System.out.println("Service层用户验证失败,无法修改密码");
//			return null;
//		}
//
//	}

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 修改密码
     */
    public boolean changePassword(String username, String password, String newpassword) throws Exception {
        // 将原始旧密码进行加密
        String passwordMd5 = Md5Util.encodeByMd5(password);

        // 将新密码同样进行加密
        String newpasswordMd5 = Md5Util.encodeByMd5(newpassword);

        // 调用dao层登录模块方法查找账号和密码对应的用户，验证用户登录，返回没修改密码的用户
        Map<String, Object> map = userDao.login(username, passwordMd5);

        if (map != null) {
            // 调用更新用户密码的方法 返回值是Map<String,Object> map对象
            int flag = userDao.changePassword(username, newpasswordMd5);
            return flag > 0 ? true : false;
        } else {
            System.out.println("Service层用户验证失败,无法修改密码");
            return false;
        }

    }


    /**
     * 记录学生分数
     */
    public boolean setStudentScore(String studentid, double score) throws SQLException {
        int flag = userDao.setStudentScore(studentid, score);
        return flag > 0 ? true : false;

    }

}
