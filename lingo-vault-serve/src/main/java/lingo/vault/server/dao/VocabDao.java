package lingo.vault.server.dao;

import java.util.List;

import lingo.vault.server.entity.Vocab;

public interface VocabDao {

    public boolean addNewVocab(Vocab newVocab);

    public boolean updateVocabList(List<Vocab> updateList);

    public List<Vocab> searchByWord(String word);
}
