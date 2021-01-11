package cn.BSProject.Service;

import java.util.List;
import java.util.Map;

import cn.BSProject.Dao.QuestionDao;
import cn.BSProject.Entity.Question;

public class QuestionService {
    Question question = new Question();
    private QuestionDao quetdao = new QuestionDao();

    /**
     * 查询题目年份
     *
     * @return
     * @throws Exception
     */
    public List<Object> QuestionTime() throws Exception {
        List<Object> map = quetdao.QuestionTime();
        return map;
    }

    /**
     * 修改题目
     *
     * @param question
     * @return
     * @throws Exception
     */
    public boolean UpdateQuestion(Question question) throws Exception {
        int i = quetdao.UpdateQuestion(question);
        return i > 0 ? true : false;
    }

    /**
     * 删除题目
     *
     * @param qid
     * @return
     * @throws Exception
     */
    public boolean dropQuestion(int qid) throws Exception {
        int i = quetdao.dropQuestion(qid);
        return i > 0 ? true : false;
    }

    /**
     * 增加题目
     *
     * @param question
     * @return
     * @throws Exception
     */
    public boolean setQuestion(Question question) throws Exception {
        int i = quetdao.setQuestion(question);
        return i > 0 ? true : false;
    }

    /**
     * 查询题目(qid获取)
     *
     * @param qid
     * @return
     * @throws Exception
     */
    public Map<String, Object> getQuestionQid(int qid) throws Exception {
        Map<String, Object> map = quetdao.getQuestionQid(qid);
        return map;
    }

    /**
     * 查询题目(所有)
     *
     * @return
     * @throws Exception
     */
    public List<Question> getQuestionAll() throws Exception {
        List<Question> questionall = quetdao.getQuestionAll();
        return questionall;
    }

    /**
     * 查询题目(随机抽取)
     *
     * @param qid
     * @return
     * @throws Exception
     */
    public List<Question> getTest(int count) throws Exception {
        List<Question> questionall = quetdao.getTest(count);
        return questionall;
    }

    /**
     * 查询题目(time抽取)
     *
     * @param time
     * @return
     * @throws Exception
     */
    public List<Question> getQuestionTime(int time) throws Exception {
        List<Question> questionall = quetdao.getQuestionTime(time);
        return questionall;
    }
}