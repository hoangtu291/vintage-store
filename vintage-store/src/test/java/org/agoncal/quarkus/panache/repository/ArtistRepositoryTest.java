package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        long count = artistRepository.count();
        int listAll = artistRepository.listAll().size();

        assertEquals(count, listAll);
        assertEquals(artistRepository.listAllSorted().size(), count);

        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);
        assertNotNull(artist.getId());

        assertEquals(count+1, artistRepository.count());

        artist = artistRepository.findById(artist.getId());
        assertEquals("name", artist.getName());

        artistRepository.deleteById(artist.getId());
        assertEquals(count, artistRepository.count());
    }

}