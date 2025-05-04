package lingo.vault.server.entity;

import java.util.Objects;

import lombok.Data;

@Data
public class VocabId {

    private String word;
    private String meaning;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VocabId that = (VocabId) o;
        return Objects.equals(word, that.word) && Objects.equals(word, that.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, meaning);
    }
}
