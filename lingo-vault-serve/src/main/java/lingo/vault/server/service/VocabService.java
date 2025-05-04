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
            entityManager.persist(newVocab);
            UpdateVocabSocket.broadcast("change");
            return true;
        } catch (Exception e) {
            return false;
        }
         }

    public boolean checkExistWord(String word, String meaning) {
        String sql = "select count(*)  from vocab where word =? and meaning =?";
        long count = (long) entityManager.createNativeQuery(sql).setParameter(1, word).setParameter(2, meaning).getSingleResult();
        return count>0;
    }
    
    @SuppressWarnings("unchecked")
    public List<Vocab> searchByWord(String word) {
        String sql = "select * from vocab where word like ?";
        return entityManager.createNativeQuery(sql,Vocab.class).setParameter(1, "%"+word+"%").getResultList();
       
    }

}
