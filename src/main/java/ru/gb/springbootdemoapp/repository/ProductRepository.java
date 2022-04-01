package ru.gb.springbootdemoapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.Category;
import ru.gb.springbootdemoapp.model.OrderStatus;
import ru.gb.springbootdemoapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("select p from Product p left join fetch p.category")
  List<Product> findAll();

  @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.title = :title")
  List<Product> findProductsByTitle(@Param("title") String title);
}
