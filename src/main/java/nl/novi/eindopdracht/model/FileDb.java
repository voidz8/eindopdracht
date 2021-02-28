package nl.novi.eindopdracht.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private byte[] data;

    @Getter
    @Setter
    private String orderNumber;


    public FileDb() {
    }

    public FileDb(String name, String type, byte[] data) {
        this.fileName = name;
        this.fileType = type;
        this.data = data;
    }

    public FileDb(String fileName, String fileType, byte[] data, String orderNumber) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return fileId;
    }
}
