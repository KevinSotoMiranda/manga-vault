package com.example.mangavault.collection;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mangavault.book.BookRepository;
import com.example.mangavault.user.UserRepository;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository, 
                            UserRepository userRepository, 
                            BookRepository bookRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Collection> getCollections() {
        return this.collectionRepository.findAll();
    }

    public Collection createCollection(Collection collection) {
        Optional<Collection> collectionOptional = this.collectionRepository.findByUserIdAndBookId(collection.getUserId(), collection.getBookId());


        if(!this.bookRepository.existsById(collection.getBookId())) {
            throw new IllegalStateException("Book does not exist"); // TODO: Create custom exceptions
        } 

        if(!this.userRepository.existsById(collection.getUserId())) {
            throw new IllegalStateException("User does not exist"); // TODO: Create custom exceptions
        }

        if(collectionOptional.isPresent()) {
            throw new IllegalStateException("Collection has already been created"); // TODO: Create custom exceptions
        }

        return this.collectionRepository.save(collection);
    }

    public void updateCollection(Long collectionId, Collection collection) {
        Optional<Collection> collectionOptional = this.collectionRepository.findById(collectionId);

        if(!collectionOptional.isPresent()) {
            throw new IllegalStateException(String.format("Collection could not be found with id: %d", collectionId)); // TODO: Create custom exceptions
        } 
     
        Collection existingCollection = collectionOptional.get();
        existingCollection.setBookId(collection.getBookId());
        existingCollection.setUserId(collection.getUserId());

        this.collectionRepository.save(existingCollection);
    }

    public void deleteCollection(Long collectionId) {
        Optional<Collection> collectionOptional = this.collectionRepository.findById(collectionId);

        if(!collectionOptional.isPresent()) {
            throw new IllegalStateException(String.format("Collection could not be found with id: %d", collectionId)); // TODO: Create custom exceptions
        } 

        this.collectionRepository.deleteById(collectionId);
    }
}
