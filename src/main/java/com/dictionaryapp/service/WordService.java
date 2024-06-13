package com.dictionaryapp.service;

import com.dictionaryapp.model.bindingModels.AddWordModel;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.viewModels.WordViewModel;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public WordService(WordRepository wordRepository, ModelMapper modelMapper, LanguageService languageService, CurrentUser currentUser, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.languageService = languageService;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    public List<WordViewModel> getAllWords() {
        return wordRepository.findAll()
                .stream()
                .map(word -> modelMapper.map(word, WordViewModel.class))
                .collect(Collectors.toList());
    }

    public void removeWord(Long id) {
        wordRepository.deleteById(id);
    }

    public void addWord(AddWordModel wordModel) {
        Word word = modelMapper.map(wordModel, Word.class);
        word.setLanguage(languageService.getByLanguageName(wordModel.getLanguage()));
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow();
        word.setAddedBy(user);
        wordRepository.saveAndFlush(word);
    }

    public void removeAllWords() {
        wordRepository.deleteAll();
    }
}
