package br.com.productadmin.product;

import org.springframework.data.jpa.repository.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByIdAndInventoryGreaterThanEqual(Long productId, int quantity);

    @Modifying
    @Query("update Product p set p.inventory = p.inventory + ?2 where p.id = ?1")
    void incrementInventory(Long productId, int quantity);

    @Modifying
    @Query("update Product p set p.inventory = p.inventory - ?2 where p.id = ?1")
    void decrementInventory(Long productId, int quantity);
}