package bmo;

public class Investor extends Adult {

    private String brokerageId;

    public Investor(String clientId, String name, String email, String phone, String brokerageId) {
        super(clientId, name, email, phone);
        this.brokerageId = brokerageId;
    }

    public String getBrokerageId()         { return brokerageId; }
    public void   setBrokerageId(String b) { this.brokerageId = b; }
}
