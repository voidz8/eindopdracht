package nl.novi.eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "debtor_number", nullable = false)
    private Long debtorNumber;

    @Getter
    @Setter
    @OneToMany(targetEntity = Order.class,
            fetch=FetchType.LAZY,
            cascade=CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "client")
    private Set<Order> orders = new HashSet<>();

    public Client() {
    }

    public Client(Set<Order> orders) {
        this.orders = orders;
    }

    public Client(String companyName, String email, Long debtorNumber) {
        this.companyName = companyName;
        this.email = email;
        this.debtorNumber = debtorNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", debtorNumber=" + debtorNumber +
                '}';
    }
}
