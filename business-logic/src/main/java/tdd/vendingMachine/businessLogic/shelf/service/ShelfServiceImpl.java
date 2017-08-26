package tdd.vendingMachine.businessLogic.shelf.service;

import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Default {@link ShelfService} implementation.
 */
class ShelfServiceImpl implements ShelfService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Product> insertProducts(VendingMachineShelf shelf, Collection<Product> products) {
        if (shelf.getProducts().size() == shelf.getSize()) {
            return products;
        }
        int shelfSpace = shelf.getSize() - shelf.getProducts().size();
        int insertionSize = products.size() > shelfSpace ? shelfSpace : products.size();
        products.stream().limit(insertionSize).forEach(shelf::addProduct);
        return products.stream().skip(insertionSize).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product dispenseProduct(VendingMachineShelf shelf) {
        return shelf.getProduct().get();
    }
}
