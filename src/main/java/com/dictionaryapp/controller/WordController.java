package com.dictionaryapp.controller;

import com.dictionaryapp.model.bindingModels.AddWordModel;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {
    private final WordService wordService;
    private final CurrentUser currentUser;

    public WordController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("wordModel")
    public AddWordModel wordModel() {
        return new AddWordModel();
    }

    @GetMapping("/add")
    public String getAddForm() {
        //If user is not logged in.
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "word-add";
    }

    @PostMapping("/add")
    public String addWord(@Valid AddWordModel wordModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        //If use is not logged in.
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordModel", wordModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordModel", wordModel);
            //redirect to words/add, if there are errors!
            return "redirect:/words/add";
        }
        //If everything is fine, add the word and redirect to the home page;
        wordService.addWord(wordModel);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeWord(@PathVariable Long id) {
        //If user is not logged in.
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        wordService.removeWord(id);
        return "redirect:/home";
    }
}
