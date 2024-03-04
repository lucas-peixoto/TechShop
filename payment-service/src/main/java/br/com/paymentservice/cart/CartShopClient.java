package br.com.paymentservice.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("productshop")
public interface CartShopClient {

    @RequestMapping(method = RequestMethod.GET, path = "/cart")
    List<Cart> getCarts();

    @RequestMapping(method = RequestMethod.GET, path = "/cart/items/{id}")
    List<CartItem> getItemsByCartId();
}
