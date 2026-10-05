package bmo;

public class SmallBusiness extends Adult {

    private String businessName;
    private String businessNumber;

    public SmallBusiness(String clientId, String name, String email, String phone, String businessName, String businessNumber) {
        super(clientId, name, email, phone);
        this.businessName   = businessName;
        this.businessNumber = businessNumber;
    }

    public String getBusinessName()           { return businessName; }
    public String getBusinessNumber()         { return businessNumber; }
    public void   setBusinessName(String b)   { this.businessName = b; }
    public void   setBusinessNumber(String b) { this.businessNumber = b; }
}
