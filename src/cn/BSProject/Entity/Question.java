package cn.BSProject.Entity;


public class Question {

    private int qid;
    private String question;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String answer;
    private String method;
    private int num;
    private Integer time;


    //------------------------------------------------------------------
    private int ordnum;

    public int getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(int ordnum) {
        this.ordnum = ordnum;
    }

//	// 使用 java 8 排序
//	   private void sortUsingJava8(List<Question> question){
//	      Collections.sort(question, (q1, q2) -> {(q1.qid - q2.qid).compareTo(0)});
//	   }
//	
//	
//	private Object compareTo(int i) {
//		return null;
//	}
    //---------------------------------------------------------------------

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Question() {
        super();
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
