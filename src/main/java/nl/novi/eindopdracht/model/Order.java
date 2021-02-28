package nl.novi.eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "order_number", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderNumber;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "companyName")
    private Client client;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "drawing_number"))
    private Set<Product> products = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
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

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Planning planning;


    public void addProduct(Product product){this.products.add(product);}

    public Order() {
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
                ", products=" + products +
                ", operations=" + operations +
                ", productionDate=" + productionDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}