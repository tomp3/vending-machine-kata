package tdd.model.vendingMachine.machine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import tdd.model.vendingMachine.common.Dimension;
import tdd.model.vendingMachine.product.ProductType;
import tdd.model.vendingMachine.product.api.Product;

import java.util.*;

/**
 * Vending machine shelve implementation.
 * Consists of shelve's dimension and products queue.
 */
@RequiredArgsConstructor
public class Shelve {
    @Getter
    private final ProductType productType;
    @Getter
    private final Dimension dimension;
    /**
     * {@link Queue} (FIFO) of shelve's products.
     */
    private final Queue<Product> products = new LinkedList<>();

    /**
     * Adds multiple products to the shelve.
     *
     * @param products products.
     */
    public void addProducts(Product... products) {
        List<Product> productsList = Arrays.asList(products);
        if (productsList.stream().anyMatch(p -> BooleanUtils.isFalse(p.getProductType().equals(this.productType)))) {
            throw new IllegalArgumentException(String.format("All products must be of shelve's product type (%s)", productType));
        }
        productsList.forEach(this::addProduct);
    }

    /**
     * Adds single product to the shelve.
     *
     * @param product product.
     */
    public void addProduct(Product product) {
        if (BooleanUtils.isFalse(product.getProductType().equals(this.productType))) {
            throw new IllegalArgumentException(String.format("Product must be of shelve's product type (%s)", productType));
        }
        products.add(product);
    }

    public Optional<Product> peekProduct() {
        return Optional.ofNullable(products.peek());
    }

    public Optional<Product> getProduct() {
        return Optional.ofNullable(products.poll());
    }

    public Collection<Product> getProducts() {
        return products;
    }

}
