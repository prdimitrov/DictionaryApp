package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @PostConstruct
    private void initLanguages() {
        if (languageRepository.count() == 0) {
            Arrays.stream(LanguageName.values())
                    .forEach(languageName -> {
                        Language language = new Language();
                        language.setName(languageName);
                        switch (languageName) {
                            case FRENCH:
                                language.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                                break;
                            case GERMAN:
                                language.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                                break;
                            case ITALIAN:
                                language.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                                break;
                            case SPANISH:
                                language.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                                break;
                        }
                        languageRepository.save(language);
                    });
        }
    }


    public Language getByLanguageName(LanguageName languageName) {
        return languageRepository.findByName(languageName);
    }
}
