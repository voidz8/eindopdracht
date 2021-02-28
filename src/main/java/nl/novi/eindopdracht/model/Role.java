
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
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Column(name = "role_id")
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private ERole name;

    @Getter
    @Setter
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public Role(){}

    @Override
    public String toString() {
        return "Role: " + name;
    }
}
