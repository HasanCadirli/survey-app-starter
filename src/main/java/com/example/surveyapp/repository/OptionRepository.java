package com.example.surveyapp.repository;

import com.example.surveyapp.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}