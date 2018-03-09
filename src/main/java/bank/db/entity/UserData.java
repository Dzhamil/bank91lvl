package bank.db.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name = "user_data")
public class UserData {

    public int id;
    public UserPersonal user_personal;
    private Collection <RequestBankAccount> request_bank_account = new ArrayList<>();
    public String login;
    public String password;
    public String date_reg;
    private boolean enabled;
    private Collection <BankAccount> bank_account = new ArrayList<>();
    private Collection <BankTransfer> bank_transfer = new ArrayList<>();
    private Collection <RolesData> roles_data = new ArrayList<>();
    private String confirmPassword;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public UserPersonal getUser_personal() {
        return user_personal;
    }
    public void setUser_personal(UserPersonal user_personal) {
        this.user_personal = user_personal;
    }

    @OneToMany(mappedBy = "user_data")
    public Collection<RequestBankAccount> getRequest_bank_account() {
        return request_bank_account;
    }
    public void setRequest_bank_account(Collection<RequestBankAccount> request_bank_account) {
        this.request_bank_account = request_bank_account;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_reg() {
        return date_reg;
    }
    public void setDate_reg(String date_reg) {
        this.date_reg = date_reg;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(mappedBy = "user_data")
    public Collection<BankAccount> getBank_account() {
        return bank_account;
    }
    public void setBank_account(Collection<BankAccount> bank_account) {
        this.bank_account = bank_account;
    }

    @OneToMany(mappedBy = "user_data")
    public Collection<BankTransfer> getBank_transfer() {
        return bank_transfer;
    }
    public void setBank_transfer(Collection<BankTransfer> bank_transfer) {
        this.bank_transfer = bank_transfer;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user_data"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    public Collection<RolesData> getRoles_data() {
        return roles_data;
    }
    public void setRoles_data(Collection<RolesData> roles_data) {
        this.roles_data = roles_data;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserData() {
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", user_personal=" + user_personal +
                ", request_bank_account=" + request_bank_account +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date_reg='" + date_reg + '\'' +
                ", enabled=" + enabled +
                ", bank_account=" + bank_account +
                ", bank_transfer=" + bank_transfer +
                ", roles_data=" + roles_data +
                '}';
    }
}
