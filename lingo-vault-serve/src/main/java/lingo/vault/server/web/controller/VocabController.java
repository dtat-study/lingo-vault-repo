package lingo.vault.server.web.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lingo.vault.server.entity.Vocab;
import lingo.vault.server.service.VocabService;
import lingo.vault.server.web.controller.dto.VocabRestDto;

@RestController
@CrossOrigin(origins = "*")
public class VocabController {

    @Autowired
    private VocabService vocabService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/checkExistWord", produces = "application/json")
    public boolean checkExistWord(@RequestBody Map<String, String> request) throws SQLException {
        return vocabService.checkExistWord(request.get("word"), request.get("meaning"));
    }

    @PostMapping(value = "/addNewVocab", produces = "application/json")
    public boolean addNewVocab(@RequestBody VocabRestDto newVocab) throws SQLException {
        vocabService.addNewVocab(modelMapper.map(newVocab, Vocab.class));
        return true;
    }

    @PostMapping(value = "/searchByWord", produces = "application/json")
    public List<VocabRestDto> searchByWord(@RequestBody Map<String, String> request) throws SQLException {
        List<Vocab> vocabList = vocabService.searchByWord(request.get("word"));
        return modelMapper.map(vocabList, new TypeToken<List<VocabRestDto>>() {
        }.getType());
    }
   
    
}
