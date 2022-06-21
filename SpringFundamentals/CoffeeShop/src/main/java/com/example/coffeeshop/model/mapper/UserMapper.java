package com.example.coffeeshop.model.mapper;

import com.example.coffeeshop.model.dto.UserRegisterDTO;
import com.example.coffeeshop.model.entites.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRegisterDtoToUser(UserRegisterDTO dto);

}
