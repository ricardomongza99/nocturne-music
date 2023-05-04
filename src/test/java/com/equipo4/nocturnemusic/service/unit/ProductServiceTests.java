package com.equipo4.nocturnemusic.service.unit;

import com.equipo4.nocturnemusic.model.Product;
import com.equipo4.nocturnemusic.repository.ProductRepository;
import com.equipo4.nocturnemusic.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Guitar");

        Product product2 = new Product();
        product2.setId(1L);
        product2.setName("Keyboard");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Guitar");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findById(1L);

        assertEquals("Guitar", result.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Guitar");

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.save(product);

        assertEquals("Guitar", result.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteById() {
        productService.deleteById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
