package lingo.vault.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lingo.vault.server.dao.VocabDao;
import lingo.vault.server.entity.Vocab;
import lingo.vault.server.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {

    @Autowired
    VocabDao vocabDao;

    public boolean addNewVocab(Vocab newVocab) {
        return vocabDao.addNewVocab(newVocab);
    }

    public boolean updateVocabList(List<Vocab> updateList) {
        return vocabDao.updateVocabList(updateList);
    }

    public List<Vocab> searchByWord(String word) {
        return vocabDao.searchByWord(word);
    }
}
