
package nl.novi.eindopdracht.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(){}

    public Role(ERole name) {
        this.name = name;
    }
}
