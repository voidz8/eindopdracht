package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Planning {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;

    @Getter
    @Setter
    @OneToMany(targetEntity = Order.class,
            fetch= FetchType.EAGER,
            cascade= CascadeType.ALL,
            mappedBy = "planning")
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order){this.orders.add(order);
    order.setPlanning(this);}

    public Planning() {
    }

    @Override
    public String toString() {
        return "Planning{" +
                "id=" + id +
                ", date=" + date +
                ", orders=" + orders +
                '}';
    }
}
