package com.example.mangavault.collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    } 

    @PostMapping
    public ResponseEntity<Collection> createCollection(@RequestBody Collection collection) {
        Collection collectionCreated = this.collectionService.createCollection(collection);
        return new ResponseEntity<>(collectionCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Collection>> getCollections() {
        List<Collection> collections = this.collectionService.getCollections();
        return ResponseEntity.ok(collections);
    }

    @PutMapping(path = "{collectionId}")
    public ResponseEntity<Collection> updateCollection(@PathVariable("collectionId") Long collectionId, @RequestBody Collection collection) {
        this.collectionService.updateCollection(collectionId, collection);
        return ResponseEntity.ok(collection);
    }
    
    @DeleteMapping(path = "{collectionId}")
    public void deleteCollection(@PathVariable("collectionId") Long collectionId) {
        this.collectionService.deleteCollection(collectionId);
    } 
    
}
