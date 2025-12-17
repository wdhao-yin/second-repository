package xml_dom4j_test;

public class Student {
    private String name;
    private String gender;
    private String phoneid;

    public Student() {
    }

    public Student(String name, String gender, String phoneid) {
        this.name = name;
        this.gender = gender;
        this.phoneid = phoneid;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return phoneid
     */
    public String getPhoneid() {
        return phoneid;
    }

    /**
     * 设置
     * @param phoneid
     */
    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid;
    }

    public String toString() {
        return "Student{name = " + name + ", gender = " + gender + ", phoneid = " + phoneid + "}";
    }
}
