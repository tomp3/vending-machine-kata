package tdd.vendingMachine.businessLogic.shelf.service;

import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;

import java.util.Collection;

/**
 * Shelf service responsible for performing operations on vending machine shelves.
 */
public interface ShelfService {

    /**
     * Creates new instance of default ShelfService implementation ({@link ShelfServiceImpl}).
     *
     * @return new instance of default ShelfService implementation ({@link ShelfServiceImpl}).
     */
    static ShelfService newShelfService() {
        return new ShelfServiceImpl();
    }

    /**
     * Inserts products to the shelf.
     * Inserts products to fill the shelf's size and returns products that could not be inserted.
     *
     * @param shelf    vending machine shelf.
     * @param products products to be inserted to the shelf.
     * @return any products, which could not be inserted to the shelf due to filling all shelf slots.
     */
    Collection<Product> insertProducts(VendingMachineShelf shelf, Collection<Product> products);

    /**
     * Dispenses one product from the shelf.
     * Removes product from shelf object.
     *
     * @param shelf vending machine shelf.
     * @return dispensed product.
     */
    Product dispenseProduct(VendingMachineShelf shelf);
}
