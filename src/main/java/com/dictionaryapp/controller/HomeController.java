package com.dictionaryapp.controller;

import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.model.viewModels.WordViewModel;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            //If user is not logged in
            return "redirect:/";
        }
        //If the user is logged in
        List<WordViewModel> wordsList = wordService.getAllWords();

        //Filtering the words, based on their language.
        List<WordViewModel> germanWords = wordsList
                .stream()
                .filter(word -> word.getLanguage().getName() == LanguageName.GERMAN)
                .collect(Collectors.toList());

        List<WordViewModel> italianWords = wordsList
                .stream()
                .filter(word -> word.getLanguage().getName() == LanguageName.ITALIAN)
                .collect(Collectors.toList());

        List<WordViewModel> frenchWords = wordsList
                .stream()
                .filter(word -> word.getLanguage().getName() == LanguageName.FRENCH)
                .collect(Collectors.toList());

        List<WordViewModel> spanishWords = wordsList
                .stream()
                .filter(word -> word.getLanguage().getName() == LanguageName.SPANISH)
                .collect(Collectors.toList());

        //Words attribute
        model.addAttribute("germanWords", germanWords);
        model.addAttribute("italianWords", italianWords);
        model.addAttribute("frenchWords", frenchWords);
        model.addAttribute("spanishWords", spanishWords);
        //Words count attribute
        model.addAttribute("germanWordsCount", germanWords.size());
        model.addAttribute("spanishWordsCount", spanishWords.size());
        model.addAttribute("frenchWordsCount", frenchWords.size());
        model.addAttribute("italianWordsCount", italianWords.size());
        int countOfAllWords = germanWords.size() + spanishWords.size() + frenchWords.size() + italianWords.size();
        model.addAttribute("allWordsCount", countOfAllWords);
        return "home";
    }

    @GetMapping("home/remove/{id}")
    public String removeWord(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            //If user is not logged in.
            return "redirect:/";
        }
        wordService.removeWord(id);
        return "redirect:/home";
    }

    @GetMapping("/home/remove/remove-all")
    public String removeAllWords() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        wordService.removeAllWords();
        return "redirect:/home";
    }
}
