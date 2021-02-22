package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "machine")
    private EMachine machine;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "machines")
    private Set<User> users= new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "operations")
    private Set<Product> products = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "operations")
    private Set<Order> orders = new HashSet<>();

    public void addEmployee(){}

    public Machine() {
    }

    public Machine(EMachine machine) {
        this.machine = machine;
    }
}
