package br.com.productadmin.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByIdAndInventoryGreaterThanEqual(Long productId, int quantity);

    Page<Product> findByCategoriesId(Long categoryId, Pageable pageable);

    @Modifying
    @Query("update Product p set p.inventory = p.inventory + ?2 where p.id = ?1")
    void incrementInventory(Long productId, int quantity);

    @Modifying
    @Query("update Product p set p.inventory = p.inventory - ?2 where p.id = ?1")
    void decrementInventory(Long productId, int quantity);

    List<Product> findByIdIn(List<Long> ids);
}