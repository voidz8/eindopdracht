package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "product")
@Entity
public class Product {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "drawing_number")
    private String drawingNumber;

    @Getter
    @Setter
    @Column(name = "current_order_number")
    private String currentOrderNumber;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name= "product_machine",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "machine_id"))
    private Set<Machine> operations;

    @Getter
    @Setter
    @Column(name = "operation_time")
    private Duration operationTime;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "productNumber")
    private Set<Order> orders = new HashSet<>();

    public Product() {
    }

    public Product(String drawingNumber, Duration operationTime, String currentOrderNumber ) {
        this.drawingNumber = drawingNumber;
        this.operationTime = operationTime;
        this.currentOrderNumber = currentOrderNumber;
    }
}
