package bmo;

public abstract class Adult extends BMOClient {

    protected Adult(String clientId, String name, String email, String phone) {
        super(clientId, name, email, phone);
    }
}
