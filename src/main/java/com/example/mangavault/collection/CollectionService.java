package com.example.mangavault.collection;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.mangavault.book.BookRepository;
import com.example.mangavault.exception.ApiRequestException;
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

    public Collection createCollection(Collection collection) {
        Optional<Collection> collectionOptional = this.collectionRepository.findByUserIdAndBookId(collection.getUserId(), collection.getBookId());


        if(!this.bookRepository.existsById(collection.getBookId())) {
            throw new ApiRequestException("Book does not exist", HttpStatus.NOT_FOUND);
        } 

        if(!this.userRepository.existsById(collection.getUserId())) {
            throw new ApiRequestException("User does not exist", HttpStatus.NOT_FOUND);
        }

        if(collectionOptional.isPresent()) {
            throw new ApiRequestException("Collection has already been created", HttpStatus.CONFLICT);
        }

        return this.collectionRepository.save(collection);
    }

    public List<Collection> getCollections() {
        return this.collectionRepository.findAll();
    }

    public void updateCollection(Long collectionId, Collection collection) {
        Optional<Collection> collectionOptional = this.collectionRepository.findById(collectionId);

        if(!collectionOptional.isPresent()) {
            throw new ApiRequestException(String.format("Collection could not be found with id: %d", collectionId), HttpStatus.NOT_FOUND);
        } 
     
        Collection existingCollection = collectionOptional.get();

        Collection updatedCollection = Collection.builder()
            .id(existingCollection.getId())
            .userId(existingCollection.getUserId())
            .bookId(existingCollection.getBookId())
            .build();

        this.collectionRepository.save(updatedCollection);
    }

    public void deleteCollection(Long collectionId) {
        Optional<Collection> collectionOptional = this.collectionRepository.findById(collectionId);

        if(!collectionOptional.isPresent()) {
            throw new ApiRequestException(String.format("Collection could not be found with id: %d", collectionId), HttpStatus.NOT_FOUND);
        } 

        this.collectionRepository.deleteById(collectionId);
    }
}
