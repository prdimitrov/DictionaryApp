package com.dictionaryapp.service;

import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.model.viewModels.WordViewModel;
import com.dictionaryapp.repo.WordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;

    public WordService(WordRepository wordRepository, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
    }

    public List<WordViewModel> getAllWords() {
        return wordRepository.findAll()
                .stream()
                .map(word -> modelMapper.map(word, WordViewModel.class))
                .collect(Collectors.toList());
    }
}
