package cn.BSProject.Entity;

public class User {
    private int id;
    private String username;
    private String password;
    private int status;
    private String role;
    private String method;
    private String newpassword;

    //--------------------------------------
    /**
     * 排序属性
     */
    private int ordnum;

    public int getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(int ordnum) {
        this.ordnum = ordnum;
    }
    //--------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 学生表专有字段
     */
    private String studentid;
    private Double score;
    //----------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 教师表专有字段
     */
    private String teacherid;

    //----------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 学生与教师公有字段
     */
    private String name;
    private String gender;
    private int age;
    private int classid;
    private int uid;
    private String telephone;

    //管理员专有字段
    private String adminid;


    //问题
    private Question question;

    //----------------------------------------------------------------------------------------------------------------------------------------------
    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


}
