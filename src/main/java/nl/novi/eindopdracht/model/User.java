package nl.novi.eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = "app_user")
@Entity
public class User {

    @Id
    @Getter
    @Setter
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "user_machine",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "machine_id"))
    private Set<Machine> machines = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){this.roles.add(role);
    role.getUsers().add(this);}
    public void addMachine(Machine machine){this.machines.add(machine);
    machine.getUsers().add(this);}

    public User() {
    }

    public User(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String email, Set<Machine> machines, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.machines = machines;
        this.roles = roles;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", machines=" + machines +
                ", roles=" + roles +
                '}';
    }
}
