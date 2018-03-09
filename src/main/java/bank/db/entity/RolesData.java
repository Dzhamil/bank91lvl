package bank.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "roles_data")
public class RolesData {

    private Long id;
    private String role_name;
    private Collection<UserData> user_data = new ArrayList<UserData>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "id_role"),
                inverseJoinColumns = @JoinColumn(name = "id_user_data"))
    public Collection<UserData> getUser_data() {
        return user_data;
    }
    public void setUser_data(Collection<UserData> user_data) {
        this.user_data = user_data;
    }

    public RolesData() {
    }

    @Override
    public String toString() {
        return "RolesData{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", user_data=" + user_data +
                '}';
    }
}
