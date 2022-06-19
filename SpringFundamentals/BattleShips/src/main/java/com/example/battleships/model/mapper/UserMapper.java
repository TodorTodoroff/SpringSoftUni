package com.example.battleships.model.mapper;

import com.example.battleships.model.User;
import com.example.battleships.model.dto.UserRegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser (UserRegisterDTO userRegisterDTO);

}
