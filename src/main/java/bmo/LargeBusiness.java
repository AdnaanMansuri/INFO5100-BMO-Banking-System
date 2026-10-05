package bmo;

public class LargeBusiness extends Adult {

    private String businessName;
    private String businessNumber;

    public LargeBusiness(String clientId, String name, String email, String phone, String businessName, String businessNumber) {
        super(clientId, name, email, phone);
        this.businessName   = businessName;
        this.businessNumber = businessNumber;
    }

    public String getBusinessName()           { return businessName; }
    public String getBusinessNumber()         { return businessNumber; }
    public void   setBusinessName(String b)   { this.businessName = b; }
    public void   setBusinessNumber(String b) { this.businessNumber = b; }
}
