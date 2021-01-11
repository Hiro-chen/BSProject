package cn.BSProject.Entity;

public class Student {
    private int studentid;
    private String name;
    private String gender;
    private int age;
    private int score;
    private int uid;
    private int classid;
    private Classes classs;


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


    public Student() {
        super();
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public Classes getClasss() {
        return classs;
    }

    public void setClasss(Classes classs) {
        this.classs = classs;
    }


}
