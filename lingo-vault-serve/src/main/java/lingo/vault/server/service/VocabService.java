package lingo.vault.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lingo.vault.server.entity.Vocab;
import lingo.vault.server.web.socket.UpdateVocabSocket;

@Service
public class VocabService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public boolean addNewVocab(Vocab newVocab) {
        try {
            String maxId = findMaxVocabId();
            newVocab.setVocabId(createNewVocabId(maxId));
            entityManager.persist(newVocab);
            UpdateVocabSocket.broadcast("change");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateVocabList(List<Vocab> updateList) {
        try {
            
            for (Vocab vocab : updateList) {
                entityManager.merge(vocab);
            }
            UpdateVocabSocket.broadcast("change");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private String findMaxVocabId() {
        String sql = "select max(vocab_id) from vocab";
        return (String) entityManager.createNativeQuery(sql).getSingleResult();
    }

    private String createNewVocabId(String maxId) {
        long maxNumber = Long.parseLong(maxId.substring(3));
        return String.format("XXX%08d", maxNumber + 1);
    }

    @SuppressWarnings("unchecked")
    public List<Vocab> searchByWord(String word) {
        String sql = "select * from vocab where word like ?";
        return entityManager.createNativeQuery(sql, Vocab.class).setParameter(1, "%" + word + "%").getResultList();

    }

}
