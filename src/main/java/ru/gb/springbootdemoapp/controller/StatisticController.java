package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
    private final ProductService productService;

    private Long executionTime;

    public StatisticController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getStatistic(Model model) {

        // реализовал для одного сервиса, для остальных делается аналогично
        // по очереди выполняем все методы сервиса productService и суммируем время выполнения
        long totalServiceExecutionTime = 0;

        //вытаскиваем весь список товаров
        List<Product> productList = productService.getAll();
        totalServiceExecutionTime += executionTime;

        // если список не пуст ищем товар с id первого элемента списка
        Product product = productList.isEmpty() ? null : productService.findById(productList.get(0).getId()).orElse(null);
        totalServiceExecutionTime += executionTime;

        if (product != null) {
            // удаляем товар
            productService.deleteById(product.getId());
            totalServiceExecutionTime += executionTime;

            // сохраняем товар
            productService.save(product);
            totalServiceExecutionTime += executionTime;
        }

        String serviceName = productService.getClass().toString();
        serviceName = serviceName.substring(0, serviceName.indexOf("$"));

        model.addAttribute("service", serviceName);
        model.addAttribute("executionTime", totalServiceExecutionTime);
        return "/statistic";
    }

    public void setExecutionTime(Long time) {
        executionTime = time;
    }

    public Long getExecutionTime() {
        return executionTime;
    }
}
