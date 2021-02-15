package nl.novi.eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

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
import javax.persistence.Table;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "product")
@Entity
public class Product {

    @Id
    @Getter
    @Setter
    @Column(name = "drawing_number", nullable = false, unique = true)
    private String drawingNumber;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name= "product_machine",
                joinColumns = @JoinColumn(name = "drawing_number"),
                inverseJoinColumns = @JoinColumn(name = "machine_id"))
    private Set<Machine> operations;

    @Getter
    @Setter
    @Column(name = "operation_time")
    private Duration operationTime;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    public Product(String drawingNumber) {
        this.drawingNumber = drawingNumber;
    }

    public Product() {
    }

    public Product(String drawingNumber, Set<Machine> operations, Duration operationTime, Set<Order> orders) {
        this.drawingNumber = drawingNumber;
        this.operations = operations;
        this.operationTime = operationTime;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", drawingNumber='" + drawingNumber + '\'' +
                ", operations=" + operations +
                ", operationTime=" + operationTime +
                ", orders=" + orders +
                '}';
    }
}
