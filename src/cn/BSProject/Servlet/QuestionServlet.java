package cn.BSProject.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.BSProject.Dao.QuestionDao;
import cn.BSProject.Entity.Question;
import cn.BSProject.Service.QuestionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 题库操作模块
 */
@WebServlet("/Question")
public class QuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    QuestionService quetService = new QuestionService();
    QuestionDao quetDao = new QuestionDao();
    Question question = new Question();

    String successstatus = "{'status':'success'}";
    String failuretatus = "{'status':'failure'}";

    public QuestionServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("请求收到");// 测试请求是否走进来了
        InputStream inputStream = request.getInputStream();// 获取数据输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8")); // 将获取的输入流数据放进缓冲流
        StringBuffer sb = new StringBuffer("");// 定义一个空字符串缓冲容器
        String temp;// 定义字符串变量，用来比较
        while ((temp = br.readLine()) != null) {// br.readLine()会读取缓冲流里面的数据，并且复制给字符串变量temp，直到读完所有数据才会返回空，跳出循环
            sb.append(temp);// 没有跳出循环前，在上面定义的空字符串缓冲容器中不断将temp的值追加到容器sb的末尾，数据是连续性追加的
        }
        br.close();// 关闭流
        String acceptjson = sb.toString(); // 将字符串缓冲容器里的数据转换成普通字符串(json格式字符串)
        JSONObject json = JSONObject.fromObject(acceptjson);// 将上面的(json格式)字符串转为json对象
        question = (Question) JSONObject.toBean(json, Question.class); // 将json对象转为java对象
        String method = ((Question) question).getMethod();// 获取json的method参数的值，根据这个值分发处理方法
        switch (method) {
            case "getQuestionQid":
                this.getQuestionQid(request, response);
                break;
            case "getTest":
                this.getTest(request, response);
                break;
            case "setQuestion":
                this.setQuestion(request, response);
                break;
            case "dropQuestion":
                this.dropQuestion(request, response);
                break;
            case "UpdateQuestion":
                this.UpdateQuestion(request, response);
                break;
            case "QuestionTime":
                this.QuestionTime(request, response);
                break;
            case "getQuestionTime":
                this.getQuestionTime(request, response);
                break;
            case "getQuestionAll":
                this.getQuestionAll(request, response);
                break;
        }
    }

    /**
     * 查询题目年份
     */
    private void QuestionTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            List<Object> questiontime = quetService.QuestionTime();

            if (questiontime != null) {
                JSONArray json = JSONArray.fromObject(questiontime);  //将获取的题目信息转化为json数组
                returnJsonArray(json, response);    // 将查询记录响应给前端
                System.out.println("题目时间获取成功!!!");
                System.out.println(json);
            } else {
                JSONArray json = JSONArray.fromObject(questiontime);
                returnJsonArray(json, response);
                System.out.println("题目时间获取失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改题目
     */
    private void UpdateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //依次获取前端发过来的题目信息，根据题目的id来修改题目内容
            String Question = question.getQuestion();
            String optiona = question.getOptiona();
            String optionb = question.getOptionb();
            String optionc = question.getOptionc();
            String optiond = question.getOptiond();
            String answer = question.getAnswer();
            Integer qid = question.getQid();
            Integer time = question.getTime();
            System.out.println(Question);
            System.out.println(optiona);
            System.out.println(optionb);
            System.out.println(optionc);
            System.out.println(optiond);
            System.out.println(answer);
            System.out.println(qid);
            System.out.println(time);
            //从业务层获取bool值，然后进行判断
            boolean flag = quetService.UpdateQuestion(question);

            if (flag) {
                JSONObject json = JSONObject.fromObject(successstatus);
                System.out.println(json);
                returnJsonObject(json, response);

                System.out.println("题目修改成功!!!");
            } else {
                JSONObject json = JSONObject.fromObject(failuretatus);
                System.out.println(json);
                returnJsonObject(json, response);
                System.out.println("题目修改失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除题目
     */
    private void dropQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer qid = question.getQid();
            System.out.println(qid);
            boolean flag = quetService.dropQuestion(qid);

            if (flag) {
                JSONObject json = JSONObject.fromObject(successstatus);
                System.out.println(json);
                returnJsonObject(json, response);
                System.out.println("题目删除成功!!!");
            } else {
                JSONObject json = JSONObject.fromObject(failuretatus);
                System.out.println(json);
                returnJsonObject(json, response);
                System.out.println("题目删除失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加题目
     */
    private void setQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //依次获取前端发过来的题目信息
            String Question = question.getQuestion();
            String optiona = question.getOptiona();
            String optionb = question.getOptionb();
            String optionc = question.getOptionc();
            String optiond = question.getOptiond();
            String answer = question.getAnswer();
            Integer time = question.getTime();
            System.out.println(Question);
            System.out.println(optiona);
            System.out.println(optionb);
            System.out.println(optionc);
            System.out.println(optiond);
            System.out.println(answer);
            System.out.println(time);

            //从业务层获取bool值，然后进行判断
            boolean flag = quetService.setQuestion(question);

            if (flag) {

                JSONObject json = JSONObject.fromObject(successstatus);
                System.out.println(json);
                returnJsonObject(json, response);

                System.out.println("题目增加成功!!!");
            } else {
                JSONObject json = JSONObject.fromObject(failuretatus);
                System.out.println(json);
                returnJsonObject(json, response);
                System.out.println("题目增加失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询题目
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getQuestionQid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Integer qid = question.getQid();
            System.out.println(qid);

            Map<String, Object> map = quetService.getQuestionQid(qid);// 调用业务层方法，接收返回值map数据包

            if (map != null) {
                JSONObject json = JSONObject.fromObject(map);// 将返回的map对象转换成json对象,返回的是question表的信息
                returnJsonObject(json, response);    // 将查询记录响应给前端
                System.out.println(json);

                System.out.println("查询成功!!!");
            } else {
                JSONObject json = JSONObject.fromObject(map);//查询结果为空   也将结果转成空数组 响应给前端
                returnJsonObject(json, response);   //响应空数组

                System.out.println("查询失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getQuestionAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            List<Question> questionall = quetService.getQuestionAll();

            if (questionall != null) {
                JSONArray json = JSONArray.fromObject(questionall);  //将获取的题目信息转化为json数组
                returnJsonArray(json, response);    // 将查询记录响应给前端
                System.out.println("题目抽取成功!!!");
                System.out.println(json);
            } else {
                JSONArray json = JSONArray.fromObject(questionall);
                returnJsonArray(json, response);
                System.out.println("题目抽取失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Integer count = question.getQid();
            System.out.println(count);

            List<Question> questionall = quetService.getTest(count);

            if (questionall != null) {
                JSONArray json = JSONArray.fromObject(questionall);  //将获取的题目信息转化为json数组
                returnJsonArray(json, response);    // 将查询记录响应给前端
                System.out.println("题目抽取成功!!!");
                System.out.println(json);
            } else {
                JSONArray json = JSONArray.fromObject(questionall);
                returnJsonArray(json, response);
                System.out.println("题目抽取失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unused")
    private void getQuestionTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Integer time = question.getTime();
            System.out.println(time);

            List<Question> questionall = quetService.getQuestionTime(time);

            //-------------------------------------------------------------------

            List<Question> que = new ArrayList<Question>();
            for (int i = 0; i < questionall.size(); i++) {
                JSONObject q = JSONObject.fromObject(questionall.get(i));
                Question p = (Question) JSONObject.toBean(q, Question.class);
                que.add(p);
                que.get(i).setOrdnum(i + 1);
                System.out.println(que.get(i).getOrdnum());
                System.out.println(que.get(i).getQuestion());
            }
            System.out.println(que);

            //------------------------------------------------------------------

            if (questionall != null) {
                JSONArray json = JSONArray.fromObject(que);  //将获取的题目信息转化为json数组
                returnJsonArray(json, response);    // 将查询记录响应给前端
                //System.out.println("题目抽取成功!!!");
                //System.out.println(json);
            } else {
                JSONArray json = JSONArray.fromObject(questionall);
                returnJsonArray(json, response);
                System.out.println("题目抽取失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 封装响应返回单个json对象的方法(参数是json对象)
     */
    private void returnJsonObject(JSONObject json, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=utf-8");// 指定返回的格式为JSON格式
            response.setCharacterEncoding("UTF-8");// setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
            String jsonStr = json.toString();// 将json对象转成字符串
            PrintWriter out = null;// 创建写者对象，将会写出物理json文件
            out = response.getWriter();
            out.write(jsonStr);
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * 封装响应返回json数组对象的方法(参数是json数组)
     */
    private void returnJsonArray(JSONArray jsonArray, HttpServletResponse response) {
        try {
            // 遍历json数组
            // for(int i=0;i<jsonArray.size();i++) {
            // System.out.println(ja.getJSONObject(i).get("id"));
            // }

            response.setContentType("application/json;charset=utf-8");// 指定返回的格式为JSON格式
            response.setCharacterEncoding("UTF-8");

            String jsonStr = jsonArray.toString();// 将json对象转成字符串
            PrintWriter out = null;// 创建写者对象，将会写出物理json文件
            out = response.getWriter();
            out.write(jsonStr);
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
