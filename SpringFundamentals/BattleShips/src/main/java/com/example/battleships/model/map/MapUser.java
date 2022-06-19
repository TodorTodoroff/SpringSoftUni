package com.example.battleships.model.map;

import com.example.battleships.model.User;
import com.example.battleships.model.dto.UserRegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapUser {

    User userDTOtoUser(UserRegisterDTO userRegisterDTO);

}
