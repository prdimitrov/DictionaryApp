package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;

@Entity
@Table(name = "words")
public class Word extends BaseEntity {

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(name = "input_date", nullable = false)
    private LocalDate inputDate;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Language language;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User addedBy;

    public Word() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
