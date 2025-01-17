package com.example.mangavault.series;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SeriesRepositoryTests {
    @Autowired
    private SeriesRepository seriesRepository;

    @Test
    public void SeriesRepository_Save_ReturnedSavedSeries() {
        Series series = Series.builder()
            .author("Ben Smith")
            .title("The Best Thing")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Assertions.assertThat(seriesSaved).isNotNull();
        Assertions.assertThat(seriesSaved.getId()).isGreaterThan(0);
        Assertions.assertThat(seriesSaved.getAuthor()).isEqualTo(series.getAuthor());
        Assertions.assertThat(seriesSaved.getTitle()).isEqualTo(series.getTitle());
    }

    @Test
    public void SeriesRepository_SaveAll_ReturnedSavedSeries() {
        List<Series> series = List.of(
            Series.builder()
                .author("First")
                .title("Title First")
                .build(),
            Series.builder()
                .author("Second")
                .title("Title Second")
                .build(),
            Series.builder()
                .author("Third")
                .title("Title Third")
                .build()
        );

        List<Series> seriesSaved = this.seriesRepository.saveAll(series);

        Assertions.assertThat(seriesSaved).isNotEmpty();

        Assertions.assertThat(seriesSaved.get(0).getId()).isGreaterThan(0);
        Assertions.assertThat(seriesSaved.get(0).getAuthor()).isEqualTo(series.get(0).getAuthor());
        Assertions.assertThat(seriesSaved.get(0).getTitle()).isEqualTo(series.get(0).getTitle());
        Assertions.assertThat(seriesSaved.get(0).getId()).isLessThan(seriesSaved.get(1).getId());

        Assertions.assertThat(seriesSaved.get(1).getId()).isGreaterThan(seriesSaved.get(0).getId());
        Assertions.assertThat(seriesSaved.get(1).getAuthor()).isEqualTo(series.get(1).getAuthor());
        Assertions.assertThat(seriesSaved.get(1).getTitle()).isEqualTo(series.get(1).getTitle());
        Assertions.assertThat(seriesSaved.get(1).getId()).isLessThan(seriesSaved.get(2).getId());

        Assertions.assertThat(seriesSaved.get(2).getId()).isGreaterThan(seriesSaved.get(1).getId());
        Assertions.assertThat(seriesSaved.get(2).getAuthor()).isEqualTo(series.get(2).getAuthor());
        Assertions.assertThat(seriesSaved.get(2).getTitle()).isEqualTo(series.get(2).getTitle());
    }
    
    @Test
    public void Series_Repository_DeleteById_ReturnsEmptyOptional() {
        Series series = Series.builder()
            .author("First")
            .title("Some Title")    
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        this.seriesRepository.deleteById(seriesSaved.getId());

        Assertions.assertThat(this.seriesRepository.findById(seriesSaved.getId())).isEmpty();
    }

    @Test
    public void SeriesRepository_DeleteAllById_ReturnsEmptyOptional() {
        List<Series> series = List.of(
            Series.builder()
                .author("First")
                .title("First Title")
                .build(),
            Series.builder()
                .author("Second")
                .title("Second Title")
                .build(),
            Series.builder()
                .author("Third")
                .title("Third Title")
                .build()
        );

        List<Series> seriesSaved = this.seriesRepository.saveAll(series);

        List<Long> seriesIds = List.of(
            seriesSaved.get(0).getId(),
            seriesSaved.get(1).getId(),
            seriesSaved.get(2).getId()
        );

        this.seriesRepository.deleteAllById(seriesIds);

        Assertions.assertThat(this.seriesRepository.findById(seriesSaved.get(0).getId())).isEmpty();
        Assertions.assertThat(this.seriesRepository.findById(seriesSaved.get(1).getId())).isEmpty();
        Assertions.assertThat(this.seriesRepository.findById(seriesSaved.get(2).getId())).isEmpty();
    }

    @Test
    public void SeriesRepository_FindByTitleAndAuthor_ReturnSeries() {
        Series series = Series.builder()
            .author("Some Author")
            .title("Some Title")
            .build();

        Series seriesSaved = this.seriesRepository.save(series);

        Optional<Series> seriesOptional = this.seriesRepository.findByTitleAndAuthor(series.getTitle(), series.getAuthor());

        Assertions.assertThat(seriesOptional).isNotEmpty();
        Assertions.assertThat(seriesOptional.get().getId()).isEqualTo(seriesSaved.getId());
        Assertions.assertThat(seriesOptional.get().getAuthor()).isEqualTo(seriesSaved.getAuthor());
        Assertions.assertThat(seriesOptional.get().getTitle()).isEqualTo(seriesSaved.getTitle());
    }
}
