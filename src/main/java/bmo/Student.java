package bmo;

public class Student extends BMOClient {

    private String school;

    public Student(String clientId, String name, String email, String phone, String school) {
        super(clientId, name, email, phone);
        this.school = school;
    }

    public String getSchool()         { return school; }
    public void   setSchool(String s) { this.school = s; }
}
