package nl.novi.eindopdracht.model;

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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "order")
@Entity
public class Order {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "client")
    private Set<Client> client = new HashSet<>();

    @Getter
    @Setter
    @Column(name = "ordernumber")
    private long orderNumber;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "order_drawing",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id"))
    private Set<Drawing> drawingNumber = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "order_machine",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "machine_id"))
    private Set<Machine> operations = new HashSet<>();

    @Getter
    @Setter
    @Column(name = "production_date")
    private LocalDate productionDate;

    @Getter
    @Setter
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    public Order() {
    }

}