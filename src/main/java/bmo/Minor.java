package bmo;

public class Minor extends BMOClient {

    private int age;
    private String guardianName;

    public Minor(String clientId, String name, String email, String phone, int age, String guardianName) {
        super(clientId, name, email, phone);
        this.age = age;
        this.guardianName = guardianName;
    }

    public int    getAge()                  { return age; }
    public String getGuardianName()         { return guardianName; }
    public void   setAge(int a)             { this.age = a; }
    public void   setGuardianName(String g) { this.guardianName = g; }
}
