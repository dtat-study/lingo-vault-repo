package lingo.vault.server.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "languages")
public class Languages {
    @Id
    @Column(name = "lang")
    private String lang;
    
    @OneToMany(mappedBy = "languages", cascade = CascadeType.ALL)
    private List<Vocab> vocabList;
}
