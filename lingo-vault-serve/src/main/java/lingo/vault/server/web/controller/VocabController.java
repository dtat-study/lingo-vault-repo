package lingo.vault.server.web.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lingo.vault.server.entity.Vocab;
import lingo.vault.server.service.VocabService;
import lingo.vault.server.web.controller.dto.VocabRestDto;

@RestController
public class VocabController {

    @Autowired
    private VocabService vocabService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/addNewVocab", produces = "application/json")
    public boolean addNewVocab(@RequestBody VocabRestDto newVocab) {
        vocabService.addNewVocab(modelMapper.map(newVocab, Vocab.class));
        return true;
    }

    @PostMapping(value = "/updateVocab", produces = "application/json")
    public boolean updateVocab(@RequestBody List<VocabRestDto> updateList) {
        vocabService.addNewVocab(modelMapper.map(updateList, new TypeToken<List<Vocab>>() {
        }.getType()));
        return true;
    }

    @PostMapping(value = "/searchByWord", produces = "application/json")
    public List<VocabRestDto> searchByWord(@RequestBody Map<String, String> request) {
        List<Vocab> vocabList = vocabService.searchByWord(request.get("word"));
        return modelMapper.map(vocabList, new TypeToken<List<VocabRestDto>>() {
        }.getType());
    }

}
