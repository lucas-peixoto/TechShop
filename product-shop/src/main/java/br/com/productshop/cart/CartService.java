package br.com.productshop.cart;

import br.com.productshop.product.Product;
import br.com.productshop.product.ProductService;
import br.com.productshop.user.LoggedUser;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final LoggedUser loggedUser;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, LoggedUser loggedUser, ProductService productService) {
        this.cartRepository = cartRepository;
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    public Cart addItems(List<CartItemRequest> cartItemRequests) {
        String ownerEmail = loggedUser.getEmail();
        Cart cart = cartRepository.findByOwnerEmail(ownerEmail).orElseGet(() -> new Cart(ownerEmail));
        cart.addItems(cartItemRequests.stream().map(CartItemRequest::toModel).toList());

        return cartRepository.save(cart);
    }

    public Cart removeItems(List<CartItemRequest> cartItemRequests) {
        String ownerEmail = loggedUser.getEmail();
        Cart cart = cartRepository.findByOwnerEmail(ownerEmail).orElseGet(() -> new Cart(ownerEmail));
        cart.removeItems(cartItemRequests.stream().map(CartItemRequest::toModel).toList());

        return cartRepository.save(cart);
    }

    public BigDecimal getTotalCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(NotFoundException::new);
        List<CartItem> items = cart.getItems();
        List<Long> productsId = items.stream().map(CartItem::getProductId).toList();
        List<Product> products = productService.getProductsByIds(productsId);

        return calculateTotalItems(products, items);
    }

    private BigDecimal calculateTotalItems(List<Product> products, List<CartItem> items) {
        BigDecimal total = BigDecimal.ZERO;

        Map<Long, BigDecimal> productPriceMap = products.stream().collect(Collectors.toMap(Product::getId, Product::getPrice));
        for (CartItem item : items) {
            BigDecimal price = productPriceMap.getOrDefault(item.getProductId(), BigDecimal.ZERO);
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemTotal);
        }

        return total;
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
