package bank.db.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "bank_account")
public class BankAccount {


    private int id;
    private int sum;
    private UserData user_data;
    private Collection<BankTransfer> bank_transfer = new ArrayList<>();
    private boolean status;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public UserData getUser_data() {
        return user_data;
    }
    public void setUser_data(UserData user_data) {
        this.user_data = user_data;
    }

    @OneToMany(mappedBy = "bank_account",fetch = FetchType.EAGER)
    public Collection<BankTransfer> getBank_transfer() {
        return bank_transfer;
    }
    public void setBank_transfer(Collection<BankTransfer> bank_transfer) {
        this.bank_transfer = bank_transfer;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public BankAccount() {
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", sum=" + sum +
                ", user_data=" + user_data +
                ", bank_transfer=" + bank_transfer +
                ", status=" + status +
                '}';
    }
}
