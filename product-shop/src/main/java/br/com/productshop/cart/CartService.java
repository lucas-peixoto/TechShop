package br.com.productshop.cart;

import br.com.productshop.exception.NotFoundException;
import br.com.productshop.product.Product;
import br.com.productshop.product.ProductService;
import br.com.productshop.user.LoggedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartValidator cartValidator;
    private final LoggedUser loggedUser;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, CartValidator cartValidator, LoggedUser loggedUser, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartValidator = cartValidator;
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    public Cart addItems(List<CartItemRequest> cartItemRequests) {
        String ownerEmail = loggedUser.getEmail();
        Cart cart = cartRepository.findByOwnerEmail(ownerEmail).orElseGet(() -> new Cart(ownerEmail));
        List<Product> products = productService.getProductsByIds(cartItemRequests.stream().map(CartItemRequest::productId).toList());

        cartValidator.validateAddItems(cart, cartItemRequests, products).throwIfInvalid();

        Map<Long, BigDecimal> productsPriceMap = products.stream().collect(Collectors.toMap(Product::getId, Product::getPrice));
        List<CartItem> cartItems = cartItemRequests.stream()
                .map(cartItemRequest -> new CartItem(cartItemRequest.productId(), productsPriceMap.get(cartItemRequest.productId()), cartItemRequest.quantity()))
                .toList();
        cart.addItems(cartItems);

        return cartRepository.save(cart);
    }

    public Cart removeItems(List<CartItemRequest> cartItemRequests) {
        String ownerEmail = loggedUser.getEmail();
        Cart cart = cartRepository.findByOwnerEmail(ownerEmail).orElseGet(() -> new Cart(ownerEmail));

        cartValidator.validateRemoveItems(cart, cartItemRequests).throwIfInvalid();

        List<CartItem> cartItems = cartItemRequests.stream().map(cartItemRequest -> new CartItem(cartItemRequest.productId(), BigDecimal.ZERO, cartItemRequest.quantity())).toList();
        cart.removeItems(cartItems);

        return cartRepository.save(cart);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public BigDecimal getTotalCart(Long id) {
        return findById(id).getTotal();
    }

    @Transactional
    public void clear() {
        cartRepository.deleteByOwnerEmail(loggedUser.getEmail());
    }
}
