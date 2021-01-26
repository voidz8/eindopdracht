package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = "machine")
@Entity
public class Machine {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "machine")
    private String machine;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "machine")
    private Set<Employee> employees = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "drawing")
    private Set<Drawing> drawings = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "order")
    private Set<Order> orders = new HashSet<>();

    public Machine() {
    }

    public Machine(String machine, Set<Employee> employees) {
        this.machine = machine;
        this.employees = employees;
    }


    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", machine='" + machine + '\'' +
                ", employees=" + employees +
                '}';
    }
}
