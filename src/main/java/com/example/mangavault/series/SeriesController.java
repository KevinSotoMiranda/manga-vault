package com.example.mangavault.series;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "api/v1/series")
@Validated
public class SeriesController {
    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping
    public ResponseEntity<Series> createSeries(@Valid @RequestBody Series series) {
        Series createdSeries = this.seriesService.createSeries(series);
        return new ResponseEntity<>(createdSeries, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Series>> getSeries() {
        List<Series> series = this.seriesService.getSeries();
        return ResponseEntity.ok(series);
    }

    @PutMapping(path = "/{seriesId}")
    public ResponseEntity<Series> updateSeries(@PathVariable("seriesId") Long seriesId, @Valid @RequestBody Series series) {
        this.seriesService.updateSeries(seriesId, series);
        return ResponseEntity.ok(series);
    }

    @DeleteMapping(path = "/{seriesId}")
    public void deleteSeries(@PathVariable("seriesId") Long seriesId) {
        this.seriesService.deleteSeries(seriesId);
    }
}
