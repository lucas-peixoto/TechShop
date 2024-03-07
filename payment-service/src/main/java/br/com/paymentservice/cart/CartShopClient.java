package br.com.paymentservice.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@FeignClient("productshop")
public interface CartShopClient {

    @RequestMapping(method = RequestMethod.GET, path = "/cart")
    List<Cart> getCarts();

    @RequestMapping(method = RequestMethod.GET, path = "/cart/{id}")
    Optional<Cart> getCartId(Long id);

    @RequestMapping(method = RequestMethod.GET, path = "/cart/items/{id}")
    List<CartItem> getItemsByCartId();

    @RequestMapping(method = RequestMethod.GET, path = "/cart/total/{id}")
    BigDecimal getTotal(Long id);
}
