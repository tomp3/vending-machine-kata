package tdd.vendingMachine.model.machine;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import tdd.vendingMachine.model.common.Shelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.Queue;

/**
 * Vending machine shelf implementation.
 */
public class VendingMachineShelf extends Shelf {
    /**
     * Shelf's product type.
     */
    @Getter
    @Setter
    private ProductType productType;
    /**
     * {@link Queue} of shelf's products.
     * The queue is used as FIFO,
     * although insertions and gets/deletions order
     * is not crucial for the {@link VendingMachineShelf} implementation.
     */
    private final Queue<Product> products = Lists.newLinkedList();

    /**
     * Stored product price.
     */
    @Getter
    @Setter
    private BigDecimal productPrice;

    /**
     * Constructor assigning shelf's product type, size and product price.
     *
     * @param productType  product type.
     * @param size         shelf size.
     * @param productPrice product price.
     */
    public VendingMachineShelf(ProductType productType, int size, BigDecimal productPrice) {
        super(size);
        this.productType = productType;
        this.productPrice = productPrice;
    }

    /**
     * Adds multiple products to the shelf.
     *
     * @param products products.
     */
    public void addProducts(Iterable<Product> products) {
        products.forEach(this::addProduct);
    }

    /**
     * Adds single product to the shelf.
     *
     * @param product product.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Method peeking the first product on the shelf.
     *
     * @return first product on the shelf.
     */
    public Optional<Product> peekProduct() {
        return Optional.ofNullable(products.peek());
    }

    /**
     * Method retrieving the first product from the shelf.
     *
     * @return retrieved product from the shelf.
     */
    public Optional<Product> getProduct() {
        return Optional.ofNullable(products.poll());
    }

    /**
     * Returns all shelf's products - operations on the returned collection does not affect the actual shelf products.
     *
     * @return all shelf's products - operations on the returned collection does not affect the actual shelf products.
     */
    public Collection<Product> getProducts() {
        return Lists.newLinkedList(products);
    }

}
