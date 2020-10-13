package com.thiago.digitalbank.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;



@Entity
public class DocumentClient implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Doc front is required")
    private String documentFront;

    @NotEmpty(message = "Doc back is required")
    private String documentBack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentFront() {
        return documentFront;
    }

    public void setDocumentFront(String documentFront) {
        this.documentFront = documentFront;
    }

    public String getDocumentBack() {
        return documentBack;
    }

    public void setDocumentBack(String documentBack) {
        this.documentBack = documentBack;
    }
}
