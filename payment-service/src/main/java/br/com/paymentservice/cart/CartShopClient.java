package br.com.paymentservice.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("product-shop")
public interface CartShopClient {

    @RequestMapping(method = RequestMethod.GET, path = "/cart/{id}")
    Cart getCartId(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, path = "/cart/items/{id}")
    List<CartItem> getItemsByCartId(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, path = "/cart/total/{id}")
    BigDecimal getTotal(@PathVariable Long id);
}
