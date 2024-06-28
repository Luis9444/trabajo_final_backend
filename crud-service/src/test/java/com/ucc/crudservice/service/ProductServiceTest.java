package com.ucc.crudservice.service;

import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ProductServiceTest { //configura el contexto de la clase como si fuera un test

    @MockBean  //objeto simulado en las clases
    private ProductRepository productRepository;

    @Autowired   //inyecta en el repocitorio (inyecta dependensias)
    private ProductService productService;

    @BeforeEach // esta anotacion sirve para que el metodo sea ejecutado antes de cada test
    void setUp (){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProducts(){ //aqui creamos un objeto product donde inicializamos los campos una simulacion
        Product product = new Product(1L, "SKU001", "Product1", "Description1", 100.0, true);
        List<Product> products = Collections.singletonList(product); //creamos una lista que contenga solos ese producto

        when(productRepository.findAll()).thenReturn(products);//configura el mock para cuando se llame el metodo final  donde va retornar una vista de productos

        List<Product> result = productService.getProducts(); //llamamos al metodo get product del service donde nos retorna la vista
        assertEquals(1, result.size()); //esto compara lo que nosotros esperamos con la respuesta
        assertEquals("SKU001", result.get(0).getSku());// y aca compara si el id es el mismo id con el id que le pasamos en la simulacion

    }


    @Test
    public void testDeleteProduct() { //vamos a testear que el producto se elimine bien
        Long productId = 1L; //declaramos una variable tipó long

        // Ejecutar el Método a Probar
        ResponseEntity<Object> response = productService.deleteProduct(productId);//llama el metodo delete product con el id que le proporsionamos y deberia devolvernos una respuesta

        // Verificar el Comportamiento
        verify(productRepository, times(1)).deleteById(productId); //metodo de mockito que verifica que el metodo del repositorio fue llamado una sola vez
        assertEquals(HttpStatus.OK, response.getStatusCode()); //compara 2 resultados es un metodo de junit verifica si 2 valores son iguales  y los compara
        assertEquals("Product delete successfully", response.getBody());
    }

}