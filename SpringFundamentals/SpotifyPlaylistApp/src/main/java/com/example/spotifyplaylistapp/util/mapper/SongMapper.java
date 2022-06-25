package com.example.spotifyplaylistapp.util.mapper;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    Song songDtoToSong(AddSongDTO addSongDTO);

}
