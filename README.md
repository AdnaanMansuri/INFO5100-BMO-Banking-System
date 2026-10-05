# INFO 5100 — Assignment 2: UML Class Diagram
## Bank of Montreal (BMO) — Banking & Loyalty System

### Deliverables in this folder
| File | What it is |
|------|------------|
| `BMO_UML_Class_Diagram.pdf` | **Part 1 deliverable** — the UML class diagram (single page), built in Lucidchart. |
| `src/main/java/bmo/*.java` | **Part 2 (bonus)** — Java implementation of the diagram. |
| `sample-run/sample_run.txt` | Captured console output of running the program. |
| `sample-run/sample_run_1.png`, `sample-run/sample_run_2.png` | The same run shown as terminal screenshots. |
| `pom.xml` | Maven build file (lets the project open/run in NetBeans, VS Code, or the command line). |

---

## Part 1 — The class diagram

Drawn in **Lucidchart**. The diagram shows every class with its attributes and
operations, the inheritance hierarchy (hollow-triangle generalization), and the
three associations with their names and multiplicities.

### Classes & inheritance
```
Account {abstract}                 Client {abstract}
 ├─ DepositAccount {abstract}       ├─ BMOClient {abstract}
 │   ├─ SavingAccount               │   ├─ Minor
 │   ├─ CheckingAccount             │   ├─ Student
 │   └─ InvestmentAccount           │   └─ Adult {abstract}
 └─ LoanAccount                     │       ├─ Investor
                                    │       ├─ Individual
LoyaltyAccount                      │       ├─ SmallBusiness
Reward                              │       └─ LargeBusiness
                                    └─ OtherBankClient
```

### Associations (name · multiplicity · meaning)
| Name | Ends & multiplicity | Meaning |
|------|---------------------|---------|
| **owns** | `BMOClient 1` — `0..* Account` | A BMO client owns any number of accounts; every account has exactly one owner. |
| **enrolsIn** | `BMOClient 1` — `0..1 LoyaltyAccount` | A client may enrol in at most one loyalty account; a loyalty account belongs to exactly one client. |
| **redeems** | `LoyaltyAccount 0..*` — `0..* Reward` | A membership can redeem many rewards; a reward can be redeemed by many members. |

### Design decisions & assumptions (addresses "Unambiguous")
1. **`accountNumber` is unique for every account**, so it lives on the base
   `Account` class and is inherited by all account types.
2. **Nickname rule** — the domain says *every account except a loan account* can
   be nicknamed. An intermediate abstract class **`DepositAccount`** holds
   `nickName`; saving, checking and investment extend it, while
   **`LoanAccount` extends `Account` directly** and so has no nickname.
3. **Interest rate** appears only on `SavingAccount` and `LoanAccount`, as stated.
   Because these sit on two different branches, the attribute is declared on each.
4. **Client hierarchy** — `minor`, `student` and `adult` are modelled as parallel
   sub-types of `BMOClient` exactly as the description lists them. Adults
   specialise further into investor / individual / small / large business.
5. **Clients of other banks** are modelled as `OtherBankClient` (a `Client` but
   **not** a `BMOClient`), so they appear in the loyalty program's data but cannot
   own BMO accounts or enrol — enforced in the Java by type.

---

## Part 2 — Java implementation (bonus)

Package `bmo`, standard Maven layout (`src/main/java/bmo`).

### How to compile & run

**NetBeans**
1. **File ▸ Open Project…** → select this folder (it shows the Maven icon) → **Open**.
2. Right-click the project ▸ **Run** (or press **F6**).
3. Output appears in the **Output** window. (Main class: `bmo.Main`.)

**Command line (Maven)**
```bash
mvn compile exec:java
```

**Command line (plain javac)**
```bash
javac -d target/classes src/main/java/bmo/*.java
java -cp target/classes bmo.Main
```
The captured output is in `sample-run/sample_run.txt`.

### How it meets the Part 2 requirements
| Requirement | Where |
|-------------|-------|
| All classes & associations implemented | the 18 classes in `src/main/java/bmo` + the 3 associations wired bidirectionally. |
| All required attributes | each class holds the attributes shown in the diagram. |
| `add` / `remove` that **do not violate multiplicities** and report errors | `BMOClient.addAccount/removeAccount`, `BMOClient.addLoyaltyAccount/removeLoyaltyAccount`, `Account.setOwner/removeOwner`, `LoyaltyAccount.addReward/removeReward`, `Reward.addRedeemer/removeRedeemer`. Each prints an `ERROR [...]` message when an operation would break a bound. |
| Method to **list** association members | `listAccounts()`, `listLoyaltyAccounts()`, `listOwner()`, `listRewards()`, `listRedeemers()`. |

### Multiplicity violations demonstrated in the sample run
- Adding an account that already has an owner → rejected (an account has **exactly one** owner).
- A client trying to enrol in a **second** loyalty account → rejected (`0..1`).
- Another client trying to claim a loyalty account that already belongs to someone → rejected.
- Redeeming more points than the balance → rejected.
