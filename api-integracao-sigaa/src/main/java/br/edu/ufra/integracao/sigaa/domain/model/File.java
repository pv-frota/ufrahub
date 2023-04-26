package br.edu.ufra.integracao.sigaa.domain.model;

import br.edu.ufra.integracao.sigaa.domain.enumeration.FileTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="File")
public class File {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FileTypeEnum type;

    //TODO adicionar isso ao banco de dados
    @Column(name = "saved", nullable = false)
    private Date saved;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    public File() {
    }

    public File(Integer id, String username, FileTypeEnum type, Date saved, byte[] data) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.saved = saved;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FileTypeEnum getType() {
        return type;
    }

    public void setType(FileTypeEnum type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Date getSaved() {
        return saved;
    }

    public void setSaved(Date saved) {
        this.saved = saved;
    }
}
