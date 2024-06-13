package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.viewModels.WordViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}
