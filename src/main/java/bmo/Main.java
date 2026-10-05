package bmo;

public class Main {

    private static void section(String title) {
        System.out.println();
        System.out.println("==================================================================");
        System.out.println("  " + title);
        System.out.println("==================================================================");
    }

    public static void main(String[] args) {

        section("1. Create clients");
        Individual      alice = new Individual("C001", "Alice Nguyen",  "alice@mail.com",  "514-100-1000");
        Student         bob   = new Student   ("C002", "Bob Tremblay",  "bob@mail.com",    "514-100-2000", "McGill University");
        SmallBusiness   cafe  = new SmallBusiness("C003", "Cafe Mont",   "hi@cafemont.com", "514-100-3000", "Cafe Mont Inc.", "BN-778899");
        Minor           dave  = new Minor     ("C004", "Dave Junior",   "dave@mail.com",   "514-100-4000", 15, "Dana Junior");
        OtherBankClient erin  = new OtherBankClient("C005", "Erin Scotia", "erin@mail.com", "514-100-5000", "Scotiabank");

        System.out.println("Created: " + alice);
        System.out.println("Created: " + bob);
        System.out.println("Created: " + cafe);
        System.out.println("Created: " + dave);
        System.out.println("Created: " + erin + "  <- client of another bank (cannot own BMO accounts)");

        section("2. owns : add accounts (BMOClient 1 -- 0..* Account)");
        SavingAccount     sav = new SavingAccount    ("A100", 2500.0, "Rainy Day", 2.5);
        CheckingAccount   chk = new CheckingAccount  ("A101", 800.0,  "Everyday");
        InvestmentAccount inv = new InvestmentAccount("A102", 15000.0, "TFSA");
        LoanAccount       loan = new LoanAccount     ("A103", -12000.0, 6.9);

        alice.addAccount(sav);
        alice.addAccount(chk);
        alice.addAccount(inv);
        loan.setOwner(alice);

        System.out.println(alice.getName() + " now owns " + alice.listAccounts().size() + " accounts:");
        for (Account a : alice.listAccounts()) {
            System.out.println("   - " + a + "  (owner = " + a.listOwner().getName() + ")");
        }

        section("3. owns : multiplicity violation (an account has exactly 1 owner)");
        System.out.println("Bob tries to also take Alice's savings account A100...");
        boolean ok = bob.addAccount(sav);
        System.out.println("   -> add succeeded? " + ok);
        System.out.println("Bob owns " + bob.listAccounts().size() + " account(s).");

        section("4. owns : remove via Account.removeOwner(), then re-list");
        System.out.println("Alice closes her investment account A102 (driven from the Account side)...");
        inv.removeOwner();
        System.out.println(alice.getName() + " now owns " + alice.listAccounts().size() + " accounts:");
        for (Account a : alice.listAccounts()) System.out.println("   - " + a);

        section("5. enrolsIn : enrol a client (1 -- 0..1)");
        LoyaltyAccount aliceLoyalty = new LoyaltyAccount("L900");
        alice.addLoyaltyAccount(aliceLoyalty);
        System.out.println("Enrolled: " + alice.getName() + " -> " + alice.listLoyaltyAccounts());

        System.out.println("Alice tries to enrol in a SECOND loyalty account...");
        LoyaltyAccount second = new LoyaltyAccount("L901");
        ok = alice.addLoyaltyAccount(second);
        System.out.println("   -> second enrolment succeeded? " + ok);

        System.out.println("Bob tries to grab Alice's loyalty account L900...");
        ok = bob.addLoyaltyAccount(aliceLoyalty);
        System.out.println("   -> succeeded? " + ok);

        section("6. Loyalty points: earn & redeem");
        aliceLoyalty.earnPoints(500);
        System.out.println("Alice earned 500 points -> balance = " + aliceLoyalty.getPointsBalance());
        aliceLoyalty.redeemPoints(999);
        aliceLoyalty.redeemPoints(200);
        System.out.println("After redeeming 200 -> balance = " + aliceLoyalty.getPointsBalance());

        section("7. redeems : many-to-many (LoyaltyAccount 0..* -- 0..* Reward)");
        Reward movie  = new Reward("R01", "Movie Ticket", 150);
        Reward coffee = new Reward("R02", "Free Coffee",   50);

        LoyaltyAccount bobLoyalty = new LoyaltyAccount("L902");
        bob.addLoyaltyAccount(bobLoyalty);

        aliceLoyalty.addReward(movie);
        aliceLoyalty.addReward(coffee);
        coffee.addRedeemer(bobLoyalty);

        System.out.println("Alice's membership redeemed:");
        for (Reward r : aliceLoyalty.listRewards()) System.out.println("   - " + r);

        System.out.println("'" + coffee.getName() + "' was redeemed by " + coffee.listRedeemers().size() + " member(s):");
        for (LoyaltyAccount la : coffee.listRedeemers())
            System.out.println("   - member " + la.getMemberId() + " (" + la.getMember().getName() + ")");

        System.out.println("Alice un-redeems the movie ticket...");
        aliceLoyalty.removeReward(movie);
        System.out.println("Alice's membership now has " + aliceLoyalty.listRewards().size() + " reward(s).");

        System.out.println("Remove Bob from the coffee reward via Reward.removeRedeemer()...");
        coffee.removeRedeemer(bobLoyalty);
        System.out.println("'" + coffee.getName() + "' now has " + coffee.listRedeemers().size() + " member(s).");

        section("8. enrolsIn : un-enrol via removeLoyaltyAccount(la)");
        bob.removeLoyaltyAccount(bobLoyalty);
        System.out.println(bob.getName() + " loyalty account(s): " + bob.listLoyaltyAccounts());

        section("9. Summary");
        System.out.println(alice.getName() + ": " + alice.listAccounts().size() + " accounts, loyalty = " + alice.listLoyaltyAccounts());
        System.out.println(bob.getName()   + ": " + bob.listAccounts().size()   + " accounts, loyalty = " + bob.listLoyaltyAccounts());
        System.out.println(cafe.getName()  + ": " + cafe.listAccounts().size()  + " accounts, loyalty = " + cafe.listLoyaltyAccounts());
        System.out.println("Demonstration complete.");
    }
}
