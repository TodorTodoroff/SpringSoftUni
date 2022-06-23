package com.example.coffeeshop.model.mapper;

import com.example.coffeeshop.model.dto.AddOrderDTO;
import com.example.coffeeshop.model.entites.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderDtoToOrder(AddOrderDTO addOrderDTO);


}
