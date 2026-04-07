package tpaql.exercise3;

public class ProductService {

    private final ProductApiClient productApiClient;

    public ProductService(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    public Product getProduct(String productId) {
        Product product = productApiClient.getProduct(productId);

        if (product == null) {
            throw new InvalidProductFormatException("Product data is null");
        }

        if (product.getId() == null || product.getName() == null) {
            throw new InvalidProductFormatException("Product data format is incompatible");
        }

        return product;
    }
}