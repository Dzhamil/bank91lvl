package bank.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name = "user_personal")
public class UserPersonal {


    private int id;
    private String first_name;
    private String last_name;
    private String second_name;
    private Collection<UserData> user_personal = new ArrayList<UserData>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSecond_name() {
        return second_name;
    }
    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    @OneToMany(mappedBy = "user_personal",fetch = FetchType.EAGER)
    public Collection<UserData> getUser_personal() {
        return user_personal;
    }
    public void setUser_personal(Collection<UserData> user_personal) {
        this.user_personal = user_personal;
    }

    public UserPersonal() {
    }

    @Override
    public String toString() {
        return "UserPersonal{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", user_personal=" + user_personal +
                '}';
    }
}
