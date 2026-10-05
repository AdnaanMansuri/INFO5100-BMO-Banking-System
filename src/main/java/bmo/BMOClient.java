package bmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BMOClient extends Client {

    private final List<Account> accounts = new ArrayList<>();
    private LoyaltyAccount loyaltyAccount;

    protected BMOClient(String clientId, String name, String email, String phone) {
        super(clientId, name, email, phone);
    }

    public boolean addAccount(Account a) {
        if (a == null) {
            System.out.println("ERROR [owns]: cannot add a null account.");
            return false;
        }
        if (a.getOwner() == this) {
            System.out.println("ERROR [owns]: " + getName() + " already owns " + a.getAccountNumber() + ".");
            return false;
        }
        if (a.getOwner() != null) {
            System.out.println("ERROR [owns]: account " + a.getAccountNumber()
                    + " already belongs to " + a.getOwner().getName()
                    + " - an account must have exactly one owner.");
            return false;
        }
        accounts.add(a);
        a.setOwnerInternal(this);
        return true;
    }

    public boolean removeAccount(Account a) {
        if (a == null || !accounts.contains(a)) {
            System.out.println("ERROR [owns]: " + getName() + " does not own that account.");
            return false;
        }
        accounts.remove(a);
        a.setOwnerInternal(null);
        return true;
    }

    public List<Account> listAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public boolean addLoyaltyAccount(LoyaltyAccount la) {
        if (la == null) {
            System.out.println("ERROR [enrolsIn]: cannot enrol in a null loyalty account.");
            return false;
        }
        if (this.loyaltyAccount != null) {
            System.out.println("ERROR [enrolsIn]: " + getName()
                    + " is already enrolled (a client may have at most one loyalty account).");
            return false;
        }
        if (la.getMember() != null && la.getMember() != this) {
            System.out.println("ERROR [enrolsIn]: that loyalty account already belongs to "
                    + la.getMember().getName() + ".");
            return false;
        }
        this.loyaltyAccount = la;
        la.setMemberInternal(this);
        return true;
    }

    public boolean removeLoyaltyAccount(LoyaltyAccount la) {
        if (loyaltyAccount == null || loyaltyAccount != la) {
            System.out.println("ERROR [enrolsIn]: " + getName() + " is not enrolled in that loyalty account.");
            return false;
        }
        loyaltyAccount = null;
        la.setMemberInternal(null);
        return true;
    }

    public List<LoyaltyAccount> listLoyaltyAccounts() {
        List<LoyaltyAccount> result = new ArrayList<>();
        if (loyaltyAccount != null) result.add(loyaltyAccount);
        return Collections.unmodifiableList(result);
    }
}
