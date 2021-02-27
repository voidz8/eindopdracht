package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FileDb {

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String fileId;

    @Getter
    @Setter
    private String fileName;

    @Getter
    @Setter
    private String fileType;

    @Getter
    @Setter
    @Lob
    private byte[] data;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public FileDb() {
    }

    public FileDb(String name, String type, byte[] data) {
        this.fileName = name;
        this.fileType = type;
        this.data = data;
    }
}
