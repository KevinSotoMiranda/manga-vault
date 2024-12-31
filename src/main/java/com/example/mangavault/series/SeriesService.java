package com.example.mangavault.series;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.mangavault.exception.ApiRequestException;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public Series createSeries(Series series) {
        Optional<Series> seriesOptional = this.seriesRepository.findByTitleAndAuthor(series.getTitle(), series.getAuthor());

        if(seriesOptional.isPresent()) {
            throw new ApiRequestException(
                String.format("A series exists with the title: %s and author: %s", series.getTitle(), series.getAuthor()), 
                HttpStatus.CONFLICT
            ); 
        }
        
        return this.seriesRepository.save(series);
    }

    public List<Series> getSeries() {
        return this.seriesRepository.findAll();
    }
    
    public Series updateSeries(Long seriesId, Series series) {
        Optional<Series> seriesOptional = this.seriesRepository.findById(seriesId);
        
        if(!seriesOptional.isPresent()) {
            throw new ApiRequestException(String.format("Series not found with id: %d",seriesId), HttpStatus.NOT_FOUND);
        }

        Series existingSeries = seriesOptional.get();
    
        // TODO: Check changing info to already existing series with same info

        if(series.getTitle() != null && !series.getTitle().isEmpty()) {
            existingSeries.setTitle(series.getTitle());
        }   

        if(series.getAuthor() != null && !series.getAuthor().isEmpty()) {
            existingSeries.setAuthor(series.getAuthor());
        }
             
        return this.seriesRepository.save(existingSeries);
    }

    public void deleteSeries(Long seriesId) {
        Optional<Series> seriesOptional = this.seriesRepository.findById(seriesId);

        if(!seriesOptional.isPresent()) {
            throw new ApiRequestException(String.format("Series not found with id: %d", seriesId), HttpStatus.NOT_FOUND);
        }

        this.seriesRepository.deleteById(seriesId);
    }
}
