package cn.BSProject.Dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import cn.BSProject.Entity.Question;
import cn.BSProject.utils.JdbcUtil;

public class QuestionDao {
    /**
     * 修改题目
     *
     * @param quet
     * @return
     * @throws Exception
     */
    public int UpdateQuestion(Question quet) throws Exception {
        //书写一条update语句,?是占位符，需要程序动态的给值
        String sql = "update question set question = ?,optiona = ?,optionb = ?,optionc = ?,optiond = ?,answer = ? ,time = ? where qid = ?";
        //将?占需要的值，用Object[]形式写出，注意：写具体值时，要按?号的顺序，?号的顺序的表结构

        Object[] params = {
                quet.getQuestion(),
                quet.getOptiona(),
                quet.getOptionb(),
                quet.getOptionc(),
                quet.getOptiond(),
                quet.getAnswer(),
                quet.getTime(),
                quet.getQid()
        };

        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        //执行SQL语句
        return runner.update(sql, params);

    }

    /**
     * 删除题目
     */
    public int dropQuestion(int qid) throws Exception {
        String sql = "delete from question where qid = ?";  //根据前端发过来的qid，删除题目
        Object[] param = {qid};
        //在企业中，很少使用原始的JDBC操作数据库，都是使用工具类或框架来操作数据库
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        return runner.update(sql, param);
    }

    /**
     * 增加题目
     *
     * @param qid
     * @return
     * @throws Exception
     */
    public int setQuestion(Question quet) throws Exception {
        //书写一条insert语句,?是占位符，需要程序动态的给值
        String sql = "insert into question values(null,?,?,?,?,?,?,?)";
        //将?占需要的值，用Object[]形式写出，注意：写具体值时，要按?号的顺序，?号的顺序的表结构
        //System.out.println(quet.getQuestion());

        Object[] params = {
                quet.getQuestion(),
                quet.getOptiona(),
                quet.getOptionb(),
                quet.getOptionc(),
                quet.getOptiond(),
                quet.getAnswer(),
                quet.getTime()
        };

        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        //执行SQL语句
        return runner.update(sql, params);

    }

    /**
     * 查询题目(qid获取)
     *
     * @param qid
     * @return
     * @throws Exception
     */
    public Map<String, Object> getQuestionQid(int qid) throws Exception {
        String sql = "select * from question where qid=?";
        Object[] params = {
                qid
        };
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        //执行sql语句，如果查询到结果一定是一条语句，并把这条语句封装成Question对象
        //如果查询不到返回null
        return runner.query(sql, new MapHandler(), params);
    }

    /**
     * 查询题目(随机抽取)
     *
     * @param count
     * @return
     * @throws Exception
     */
    public List<Question> getTest(int count) throws Exception {
        //执行sql语句，随机抽取题目
        String sql = "select * from question order by rand() limit ?";
        //String sql="SELECT * FROM?question WHERE qid >= ((SELECT MAX(qid) FROM question)-(SELECT?MIN(qid) FROM question)) * RAND() + (SELECT MIN(qid) FROM question) ? LIMIT ?";
        Object[] params = {
                count
        };
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        //执行sql语句，如果查询到结果一定是一条语句，并把这条语句封装成Question对象
        //如果查询不到返回null
        return runner.query(sql, new BeanListHandler<Question>(Question.class), params);
    }

    /**
     * 查询题目(所有)
     *
     * @return
     * @throws Exception
     */
    public List<Question> getQuestionAll() throws Exception {
        //执行sql语句，随机抽取题目
        String sql = "select * from question order by qid";
        //String sql="SELECT * FROM?question WHERE qid >= ((SELECT MAX(qid) FROM question)-(SELECT?MIN(qid) FROM question)) * RAND() + (SELECT MIN(qid) FROM question) ? LIMIT ?";

        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        //执行sql语句，如果查询到结果一定是一条语句，并把这条语句封装成Question对象
        //如果查询不到返回null
        return runner.query(sql, new BeanListHandler<Question>(Question.class));
    }

    /**
     * 查询题目(time获取)
     *
     * @param time
     * @return
     * @throws Exception
     */
    public List<Question> getQuestionTime(int time) throws Exception {

        String sql = "select * from question where time=?";
        Object[] params = {
                time
        };
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

        return runner.query(sql, new BeanListHandler<Question>(Question.class), params);
    }

    /**
     * 查询题目年份
     *
     * @return
     * @throws Exception
     */
    public List<Object> QuestionTime() throws Exception {
        //执行sql语句，查询时间列
        String sql = "select distinct time from question order by time";

        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

        //执行sql语句，将查询结果的某列数据封装到List<>
        return runner.query(sql, new ColumnListHandler());

    }
}
