package com.example.coffeeshop.services;

import com.example.coffeeshop.Session.LoggedUser;
import com.example.coffeeshop.model.dto.AddOrderDTO;
import com.example.coffeeshop.model.entites.Order;
import com.example.coffeeshop.model.entites.User;
import com.example.coffeeshop.model.mapper.OrderMapper;
import com.example.coffeeshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AuthService authService;
    private final LoggedUser loggedUser;
    private final CategoryService categoryService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, AuthService authService, LoggedUser loggedUser, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
    }


    public void saveOrder(AddOrderDTO addOrderDTO) {

        Order order = this.orderMapper.orderDtoToOrder(addOrderDTO);
        order.setCategory(this.categoryService.findByName(addOrderDTO.getCategory()));

        User byId = this.authService.findById(this.loggedUser.getId());

        order.setEmployee(byId);

        this.orderRepository.save(order);


    }

    public List<Order> findAllSorted() {

        return this.orderRepository.findByOrderByPriceDesc();

    }

    public void readyOrder(Long id) {

        this.orderRepository.deleteById(id);
    }
}
