package bank.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_transfer")
public class BankTransfer {

    private int id;
    private UserData user_data;
    private BankAccount bank_account;
    private int sum;
    private boolean status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    public UserData getUser_data() {
        return user_data;
    }
    public void setUser_data(UserData user_data) {
        this.user_data = user_data;
    }

    @ManyToOne(optional = false)
    public BankAccount getBank_account() {
        return bank_account;
    }
    public void setBank_account(BankAccount bank_account) {
        this.bank_account = bank_account;
    }

    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public BankTransfer() {
    }

    @Override
    public String toString() {
        return "BankTransfer{" +
                "id=" + id +
                ", user_data=" + user_data +
                ", bank_account=" + bank_account +
                ", sum=" + sum +
                ", status=" + status +
                '}';
    }
}
