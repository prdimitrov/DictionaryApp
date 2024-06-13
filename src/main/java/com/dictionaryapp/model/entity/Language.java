package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageName;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "language_name", unique = true, nullable = false)
    private LanguageName languageName;


    @Column(nullable = false)
    private String description;

    @OneToMany
    @Fetch(FetchMode.JOIN)
    private List<Word> words;

    public Language() {
    }

    public LanguageName getLanguageName() {
        return languageName;
    }

    public void setLanguageName(LanguageName languageName) {
        this.languageName = languageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
