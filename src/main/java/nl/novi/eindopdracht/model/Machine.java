package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
    @ManyToMany(mappedBy = "machines", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users= new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "operations", cascade = CascadeType.PERSIST)
    private Set<Product> products = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "operations", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Order> orders = new HashSet<>();


    public Machine() {
    }

    public Machine(long id, EMachine machine) {
        this.id = id;
        this.machine = machine;
    }

    public Machine(EMachine machine) {
        this.machine = machine;
    }


    @Override
    public String toString() {
        return "Machine: " + machine ;
    }
}
