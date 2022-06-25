package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.UserLoginDTO;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session_sort_of.LoggedUser;
import com.example.spotifyplaylistapp.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final UserMapper userMapper;
    private SongRepository songRepository;


    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser, UserMapper userMapper, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.userMapper = userMapper;
        this.songRepository = songRepository;
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<User> userOpt = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (userOpt.isEmpty() || (!userOpt.get().getPassword().equals(userLoginDTO.getPassword()))) {
            return false;
        }

        this.loggedUser.login(userOpt.get());

        return true;
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {

        Optional<User> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        Optional<User> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());

        if ((byEmail.isPresent() || byUsername.isPresent()) || (!userRegisterDTO.getConfirmPassword().equals(userRegisterDTO.getPassword()))) {
            return false;
        }

        User user = this.userMapper.userDtoToUser(userRegisterDTO);

        this.userRepository.save(user);

        return true;
    }


    public List<Song> getCurrentUserSongs(Long id) {
        User byId = this.userRepository.findById(id).get();

        return byId.getPlaylist();
    }

    public void addSong(Long id) {
        User byId = this.userRepository.findById(loggedUser.getId()).get();

        Song song = this.songRepository.findById(id).get();


        Optional<Long> first = byId.getPlaylist()
                .stream()
                .map(Song::getId)
                .filter(i -> i.equals(id))
                .findFirst();

        if (first.isEmpty()) {

            byId.addSongToPlayList(song);

            this.userRepository.save(byId);
        }
    }

    public void removeSongs() {
        User byId = this.userRepository.findById(loggedUser.getId()).get();

        byId.getPlaylist().clear();

        this.userRepository.save(byId);
    }
}
