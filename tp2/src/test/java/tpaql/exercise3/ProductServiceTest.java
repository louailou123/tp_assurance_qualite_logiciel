package tpaql.exercise3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductApiClient productApiClient;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productApiClient = mock(ProductApiClient.class);
        productService = new ProductService(productApiClient);
    }

    @Test
    void shouldReturnProductWhenApiCallSucceeds() {
        Product product = new Product("P001", "Laptop", 1500.0);

        when(productApiClient.getProduct("P001")).thenReturn(product);

        Product result = productService.getProduct("P001");

        assertNotNull(result);
        assertEquals("P001", result.getId());
        assertEquals("Laptop", result.getName());
        assertEquals(1500.0, result.getPrice());

        verify(productApiClient, times(1)).getProduct("P001");
    }

    @Test
    void shouldThrowInvalidProductFormatExceptionWhenProductIsNull() {
        when(productApiClient.getProduct("P002")).thenReturn(null);

        InvalidProductFormatException exception = assertThrows(
                InvalidProductFormatException.class,
                () -> productService.getProduct("P002")
        );

        assertEquals("Product data is null", exception.getMessage());

        verify(productApiClient, times(1)).getProduct("P002");
    }

    @Test
    void shouldThrowInvalidProductFormatExceptionWhenProductFormatIsInvalid() {
        Product invalidProduct = new Product(null, "Phone", 800.0);

        when(productApiClient.getProduct("P003")).thenReturn(invalidProduct);

        InvalidProductFormatException exception = assertThrows(
                InvalidProductFormatException.class,
                () -> productService.getProduct("P003")
        );

        assertEquals("Product data format is incompatible", exception.getMessage());

        verify(productApiClient, times(1)).getProduct("P003");
    }

    @Test
    void shouldThrowProductApiExceptionWhenApiFails() {
        when(productApiClient.getProduct("P004"))
                .thenThrow(new ProductApiException("API call failed"));

        ProductApiException exception = assertThrows(
                ProductApiException.class,
                () -> productService.getProduct("P004")
        );

        assertEquals("API call failed", exception.getMessage());

        verify(productApiClient, times(1)).getProduct("P004");
    }
}