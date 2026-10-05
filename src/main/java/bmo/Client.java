package bmo;

public abstract class Client {

    private final String clientId;
    private String name;
    private String email;
    private String phone;

    protected Client(String clientId, String name, String email, String phone) {
        this.clientId = clientId;
        this.name  = name;
        this.email = email;
        this.phone = phone;
    }

    public String getClientId() { return clientId; }
    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPhone()    { return phone; }

    public void setName(String n)  { this.name = n; }
    public void setEmail(String e) { this.email = e; }
    public void setPhone(String p) { this.phone = p; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + name + " (id=" + clientId + ")";
    }
}
