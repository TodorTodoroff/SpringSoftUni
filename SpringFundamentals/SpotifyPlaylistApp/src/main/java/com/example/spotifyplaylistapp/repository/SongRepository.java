package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByPerformer(String performer);

    @Query("SELECT s from Song s where s.style.styleName = 'POP'")
    List<Song> getPopSongs();

    @Query("SELECT s from Song s where s.style.styleName = 'ROCK'")
    List<Song> getRockSongs();

    @Query("SELECT s from Song s where s.style.styleName = 'JAZZ'")
    List<Song> getJazzSongs();

    List<Song> findAllUserById(Long id);
}
