package br.com.paymentservice.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("product-admin")
public interface ProductAdminClient {

    @RequestMapping(method = RequestMethod.GET, path = "/products/{id}")
    Product getProductById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    List<Product> getProducts();
}
