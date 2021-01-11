package cn.BSProject.Entity;

public class Teacher {
    private String teacherid;
    private String name;
    private String gender;
    private int age;
    private String telephone;
    private int classid;
    private int uid;

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


    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Teacher() {
        super();
    }

}
