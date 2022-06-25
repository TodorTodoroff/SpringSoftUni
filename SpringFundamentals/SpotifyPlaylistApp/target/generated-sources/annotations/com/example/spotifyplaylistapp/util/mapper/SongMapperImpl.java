package com.example.spotifyplaylistapp.util.mapper;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-25T17:41:03+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class SongMapperImpl implements SongMapper {

    @Override
    public Song songDtoToSong(AddSongDTO addSongDTO) {
        if ( addSongDTO == null ) {
            return null;
        }

        Song song = new Song();

        song.setPerformer( addSongDTO.getPerformer() );
        song.setTitle( addSongDTO.getTitle() );
        song.setDuration( addSongDTO.getDuration() );
        song.setReleaseDate( addSongDTO.getReleaseDate() );
        song.setStyle( styleTypeEnumToStyle( addSongDTO.getStyle() ) );

        return song;
    }

    protected Style styleTypeEnumToStyle(StyleTypeEnum styleTypeEnum) {
        if ( styleTypeEnum == null ) {
            return null;
        }

        Style style = new Style();

        return style;
    }
}
