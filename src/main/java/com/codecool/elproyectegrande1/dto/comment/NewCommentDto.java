package com.codecool.elproyectegrande1.dto.comment;


import com.codecool.elproyectegrande1.entity.Dream;

public class NewCommentDto {

    private String comment;

    private Dream dream;

//    private Dreamer dreamer;


    public NewCommentDto(String comment) {
        this.comment = comment;
    }

    public NewCommentDto() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Dream getDream() {
        return dream;
    }

    public void setDream(Dream dream) {
        this.dream = dream;
    }
}

