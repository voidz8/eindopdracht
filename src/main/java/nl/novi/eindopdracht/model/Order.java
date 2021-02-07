package nl.novi.eindopdracht.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @Getter
    @Setter
    @Column(name = "order_number", nullable = false, unique = true)
    private long orderNumber;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "company_name", nullable = false)
    private Client client;


    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "drawing_number"))
    private Set<Product> drawingNumber = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "order_machine",
            joinColumns = @JoinColumn(name = "order_number"),
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

    public Order(long orderNumber, LocalDate productionDate, LocalDate deliveryDate) {
        this.orderNumber = orderNumber;
        this.productionDate = productionDate;
        this.deliveryDate = deliveryDate;
    }

    public Order(Client client, long orderNumber, LocalDate productionDate, LocalDate deliveryDate) {
        this.client = client;
        this.orderNumber = orderNumber;
        this.productionDate = productionDate;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", client=" + client +
                ", drawingNumber=" + drawingNumber +
                ", operations=" + operations +
                ", productionDate=" + productionDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}