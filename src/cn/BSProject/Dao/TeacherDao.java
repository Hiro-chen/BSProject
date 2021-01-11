package cn.BSProject.Dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.BSProject.Entity.Classes;
import cn.BSProject.Entity.Student;
import cn.BSProject.utils.JdbcUtil;

public class TeacherDao {
    // updateStudent/
    // 类内全局变量字段
    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    // ----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 按班级编号查询所有学生
     */
    public List<Student> findStudentByClassID(int classid) throws SQLException {
        String sql = "SELECT * FROM student WHERE classid = ? order by studentid asc";
        Object[] params = {classid};

        return runner.query(sql, new BeanListHandler<>(Student.class), params);
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------


    /**
     * 查找所有班级
     */

    public List<Classes> findAllClass() throws Exception {
        String sql = "select * from classes ";
        return runner.query(sql, new BeanListHandler<Classes>(Classes.class));
    }
}
