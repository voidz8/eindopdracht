package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "drawing")
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
    @Column(name = "order_number")
    private String orderNumber;

    @Getter
    @Setter
    @ManyToMany()
    private Set<Machine> operations;

    @Getter
    @Setter
    @Column(name = "operation_time")
    private Duration operationTime;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "order")
    private Set<Order> orders = new HashSet<>();

    public Product() {
    }

}
