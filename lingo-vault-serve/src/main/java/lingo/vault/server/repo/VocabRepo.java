//package lingo.vault.server.repo;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import lingo.vault.server.entity.Vocab;
//
//@Service
//public class VocabRepo {
//    private final JdbcTemplate jdbcTemplate;
//
//    public VocabRepo(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void addNewVocab(Vocab newVocab) throws SQLException {
//        String sql = "insert into vocab(word,meaning,detail,notice,example,status,classification,priority) values (?,?,?,?,?,?,?,?)";
//        jdbcTemplate.update(sql, newVocab.getWord(), newVocab.getMeaning(), newVocab.getDetail(), newVocab.getNotice(),
//                newVocab.getExample(), newVocab.getStatus(), newVocab.getClassification(), newVocab.getPriority());
//    }
//
//    public long checkExistWord(String word, String meaning) throws SQLException {
//        String sql = "select count(*)  from vocab where word =? and meaning =?";
//        long count = jdbcTemplate.queryForObject(sql, Long.class, word, meaning);
//        return count;
//    }
//    
//    public List<Vocab> searchByWord(String word) throws SQLException {
//        String sql = "select * from vocab where word like ?";
//        List<Vocab> searchResults = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vocab.class), "%"+word+"%");
//        return searchResults;
//    }
//
//}