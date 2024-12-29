package com.example.mangavault.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CollectionRepository extends JpaRepository<Collection, Long>{

    Optional<Collection> findByUserIdAndBookId(Long userId, Long bookId);

}
