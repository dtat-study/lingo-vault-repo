package lingo.vault.server.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vocab")
@IdClass(VocabId.class)
public class Vocab {

    @Id
    @Column
    private String word;
    
    @Id
    @Column
    private String meaning;

    @Column
    private String notice;

    @Column
    private String example;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;
    
    @Column
    private String priority;
    
    @Column
    private int status;

    @Column
    private String classification;
    
    @PrePersist
    public void prePersist() {
        if (created_at == null) {
            created_at = new Date();
        }
        if (updated_at == null) {
            updated_at = new Date();
        }
    }
    ;
}
