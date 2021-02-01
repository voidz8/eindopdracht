package nl.novi.eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "company_name")
    private String companyName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "debtor_number")
    private long debtorNumber;

    @Getter
    @Setter
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Order> orders;

    public Client() {
    }

    public Client(String companyName, String email, long debtorNumber) {
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
