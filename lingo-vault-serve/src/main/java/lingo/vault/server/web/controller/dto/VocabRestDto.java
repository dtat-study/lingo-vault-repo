package lingo.vault.server.web.controller.dto;

import java.util.Date;

import lombok.Data;

@Data
public class VocabRestDto {

    private String vocabId;
    private String word;
    private String meaning;
    private String notice;
    private String level;
    private String example;
    private int status;
    private String classification;
    private Date createdAt;

}
