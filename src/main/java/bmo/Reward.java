package bmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reward {

    private final String rewardId;
    private String name;
    private int pointsCost;

    private final List<LoyaltyAccount> redeemers = new ArrayList<>();

    public Reward(String rewardId, String name, int pointsCost) {
        this.rewardId   = rewardId;
        this.name       = name;
        this.pointsCost = pointsCost;
    }

    public String getRewardId()   { return rewardId; }
    public String getName()       { return name; }
    public int    getPointsCost() { return pointsCost; }
    public void   setName(String n)    { this.name = n; }
    public void   setPointsCost(int c) { this.pointsCost = c; }

    public boolean addRedeemer(LoyaltyAccount la) {
        if (la == null) {
            System.out.println("ERROR [redeems]: cannot add a null loyalty account.");
            return false;
        }
        return la.addReward(this);
    }

    public boolean removeRedeemer(LoyaltyAccount la) {
        if (la == null || !redeemers.contains(la)) {
            System.out.println("ERROR [redeems]: that loyalty account has not redeemed " + name + ".");
            return false;
        }
        return la.removeReward(this);
    }

    public List<LoyaltyAccount> listRedeemers() {
        return Collections.unmodifiableList(redeemers);
    }

    void addRedeemerInternal(LoyaltyAccount la)    { if (!redeemers.contains(la)) redeemers.add(la); }
    void removeRedeemerInternal(LoyaltyAccount la) { redeemers.remove(la); }

    @Override
    public String toString() {
        return "Reward '" + name + "' (#" + rewardId + ", cost=" + pointsCost + " pts)";
    }
}
