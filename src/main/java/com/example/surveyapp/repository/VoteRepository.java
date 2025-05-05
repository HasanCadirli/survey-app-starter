package com.example.surveyapp.repository;

import com.example.surveyapp.model.Question;
import com.example.surveyapp.model.User;
import com.example.surveyapp.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUserAndQuestion(User user, Question question);
}