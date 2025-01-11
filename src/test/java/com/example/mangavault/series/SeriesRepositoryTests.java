package com.example.mangavault.series;

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

    
}
