package ru.gb.springbootdemoapp.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springbootdemoapp.model.CustomerAddress;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.User;
import ru.gb.springbootdemoapp.repository.UserRepository;
import ru.gb.springbootdemoapp.service.CustomerAddressService;
import ru.gb.springbootdemoapp.service.OrderService;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final CustomerAddressService customerAddressService;

    public OrderController(OrderService orderService, UserRepository userRepository, CustomerAddressService customerAddressService) {
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.customerAddressService = customerAddressService;
    }

    @GetMapping
    public String getOrderPage(Principal principal, Model model) {

        User user = principal != null ? userRepository.findByLogin(principal.getName()).orElse(null) : null;

        String userEmail = user != null ? user.getEmail() : null;
        model.addAttribute("userEmail", userEmail);

        String customerAddress = user != null ? customerAddressService.findById(user.getId()).map(CustomerAddress::getAddress).orElse("") : "";
        model.addAttribute("customerAddress", customerAddress);

        return "order";
    }

    @GetMapping("/success")
    public String getOrderSuccessPage() {
        return "order-success";
    }

    @PostMapping
    public String createOrder(@RequestParam String address, @RequestParam String email, Principal principal, Model model) {
        try {
            Order order = orderService.placeOrder(address, email, principal);
            return "redirect:/order/success";
        } catch (IllegalStateException e) {
            model.addAttribute("illegalStateException", e);
            return "order";
        }
    }
}
