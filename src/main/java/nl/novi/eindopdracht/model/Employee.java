package nl.novi.eindopdracht.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = "employee")
@Entity
public class Employee {

    @Getter
    @Setter
    @Column(name = "fullName", nullable = false, unique = true)
    private String name;

    @Id
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
    @ManyToMany
    @JoinTable(name = "employee_machine",
            joinColumns = @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "machine_id"))
    private Set<Machine> machines = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Employee() {
    }

    public Employee(String name, String email, String password, Set<Machine> machines, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.machines = machines;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", machines=" + machines +
                ", roles=" + roles +
                '}';
    }
}
