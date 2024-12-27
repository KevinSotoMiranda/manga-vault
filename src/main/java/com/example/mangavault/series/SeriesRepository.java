package com.example.mangavault.series;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findByTitleAndAuthor(String title, String author);
}
