package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
//Le dice a Spring que va a ser el controlador de una API REST
@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "Manage products in the store")
public class ProductController {
    //Inyectar el servicio
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all products",
            description = "Return a list of all available products"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK) ;
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Return a product by its ID if it exist"
    )
    @ApiResponse(responseCode = "200", description = "Product Found")
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "ID of the product to be retrieved",
            example = "7", required = true)
            @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get product by category",
            description = "Returns all products in a specific category"
    )
    @ApiResponse(responseCode = "200", description = "Products Found in the category")
    @ApiResponse(responseCode = "404", description = "No product fount in the category")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "Category ID", example = "2",
            required = true)
            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    @Operation(
            summary = "Save a new product",
            description = "Registers a new product and returns the created product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Product",
                                    value = """
                            {
                            "name" : "Penguin Coke",
                            "categoryId": 2,
                            "price": "19.50",
                            "stock": 230,
                            "active": true
                            }
                            """

                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Products created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int productId, @RequestBody Product product) {
        return productService.getProduct(productId)
                .map(existingProduct -> {
                    product.setProductId(productId); // CORRECTO: usas el nombre del campo de la clase Product
                    Product updated = productService.save(product); // Reutilizas el metodo save
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delate a product by ID", description = "Delates a product if exist")
    @ApiResponse(responseCode = "200", description = "Products delated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity delete(
            @Parameter(description = "Id of the products to be delated", example = "7",
            required = true)
            @PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
