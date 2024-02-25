package br.com.productshop.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@FeignClient("product-admin")
public interface ProductAdminClient {

    @RequestMapping(method = RequestMethod.GET, path = "/products/{id}")
    Product getProductById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    Page<Product> getProducts(@RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    Page<Product> getProducts(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort);

    @RequestMapping(method = RequestMethod.GET, path = "/products/category/{categoryId}")
    Page<Product> getProductsByCategory(@PathVariable Long categoryId, @RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping(method = RequestMethod.GET, path = "/products/category/{categoryId}")
    Page<Product> getProductsByCategory(@PathVariable Long categoryId, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort);
}
