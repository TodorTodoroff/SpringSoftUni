package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.util.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final StyleService styleService;


    @Autowired
    public SongService(SongRepository songRepository, SongMapper songMapper, StyleService styleService) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
        this.styleService = styleService;

    }

    public boolean addSong(AddSongDTO addSongDTO) {

        Optional<Song> songOpt = this.songRepository.findByPerformer(addSongDTO.getPerformer());

        if (songOpt.isPresent()) {
            return false;
        }

        Song song = this.songMapper.songDtoToSong(addSongDTO);

        Style style = this.styleService.findByStyleName(addSongDTO.getStyle());

        song.setStyle(style);

        this.songRepository.save(song);

        return true;
    }

    public List<Song> getPopSongs() {
        return this.songRepository.getPopSongs();
    }

    public List<Song> getRockSongs() {
        return this.songRepository.getRockSongs();
    }

    public List<Song> getJazzSongs() {
        return this.songRepository.getJazzSongs();
    }



}
