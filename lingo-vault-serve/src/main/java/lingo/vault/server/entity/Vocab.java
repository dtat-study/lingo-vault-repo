package lingo.vault.server.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vocab")
public class Vocab {

    @Id
    @Column(name = "vocab_id")
    private String vocabId;

    @Column
    private String word;

    @Column
    private String meaning;

    @Column
    private String notice;

    @Column
    private String example;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column
    private String level;

    @Column
    private int status;

    @Column
    private String classification;
    
    @JoinColumn(name = "username")
    @ManyToOne
    private AppUser appUser;

    @JoinColumn(name = "lang")
    @ManyToOne
    private Languages languages;
    
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = new Date();
        }
        if (status == 0) {
            status = 5;
        }
        if(level == null) {
            level = "1";
        }
    }
    
    @PreUpdate
    public void preUpdate() {
        if (updatedAt == null) {
            updatedAt = new Date();
        }
    }
}
