package lingo.vault.server.web.controller.dto;

import java.util.Date;

import lombok.Data;

@Data
public class VocabRestDto {

    private String word;
    private String meaning;
    private String notice;
    private String priority;
    private String example;
    private int status;
    private String classification;
    private Date created_at;

}
