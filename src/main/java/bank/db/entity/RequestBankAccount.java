package bank.db.entity;
import javax.persistence.*;

@Entity
@Table(name = "request_bank_account")
public class RequestBankAccount {

    private int id;
    private int sum;
    private UserData user_data;

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public UserData getUser_data() {
        return user_data;
    }
    public void setUser_data(UserData user_data) {
        this.user_data = user_data;
    }

    public RequestBankAccount() {
    }

    @Override
    public String toString() {
        return "RequestBankAccount{" +
                "id=" + id +
                ", sum=" + sum +
                ", user_data=" + user_data.id +
                '}';
    }
}
