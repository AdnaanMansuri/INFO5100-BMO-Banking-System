package bmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoyaltyAccount {

    private final String memberId;
    private int pointsBalance;

    private BMOClient member;
    private final List<Reward> rewards = new ArrayList<>();

    public LoyaltyAccount(String memberId) {
        this.memberId = memberId;
        this.pointsBalance = 0;
    }

    public String getMemberId()      { return memberId; }
    public int    getPointsBalance() { return pointsBalance; }

    public BMOClient getMember() { return member; }
    void setMemberInternal(BMOClient c) { this.member = c; }

    public void earnPoints(int p) {
        if (p <= 0) { System.out.println("ERROR [points]: points to earn must be positive."); return; }
        pointsBalance += p;
    }

    public boolean redeemPoints(int p) {
        if (p <= 0) { System.out.println("ERROR [points]: points to redeem must be positive."); return false; }
        if (p > pointsBalance) {
            System.out.println("ERROR [points]: insufficient points (have " + pointsBalance + ", need " + p + ").");
            return false;
        }
        pointsBalance -= p;
        return true;
    }

    public boolean addReward(Reward r) {
        if (r == null) {
            System.out.println("ERROR [redeems]: cannot add a null reward.");
            return false;
        }
        if (rewards.contains(r)) {
            System.out.println("ERROR [redeems]: reward " + r.getName() + " is already linked to member " + memberId + ".");
            return false;
        }
        rewards.add(r);
        r.addRedeemerInternal(this);
        return true;
    }

    public boolean removeReward(Reward r) {
        if (r == null || !rewards.contains(r)) {
            System.out.println("ERROR [redeems]: member " + memberId + " has not redeemed that reward.");
            return false;
        }
        rewards.remove(r);
        r.removeRedeemerInternal(this);
        return true;
    }

    public List<Reward> listRewards() {
        return Collections.unmodifiableList(rewards);
    }

    @Override
    public String toString() {
        return "LoyaltyAccount #" + memberId + " (points=" + pointsBalance + ")";
    }
}
