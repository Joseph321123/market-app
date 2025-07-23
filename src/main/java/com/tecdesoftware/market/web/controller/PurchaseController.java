package com.tecdesoftware.market.web.controller;

    import com.tecdesoftware.market.domain.Purchase;
    import com.tecdesoftware.market.domain.service.PurchaseService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.ExampleObject;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    @RestController
    @RequestMapping("/purchases")
    @Tag(name = "Purchase Controller", description = "Manage purchase transactions")
    public class PurchaseController {

        @Autowired
        private PurchaseService purchaseService;

        //si quiero que funcione igual con solo /purchases
        //@GetMapping
        //public ResponseEntity<List<Purchase>> getAllBase() {
        //    return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
        //}


        @GetMapping("/all")
        @Operation(
                summary = "Get all purchases",
                description = "Returns a list of all registered purchases"
        )
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of purchases")
        @ApiResponse(responseCode = "500", description = "Internal server error")
        public ResponseEntity<List<Purchase>> getAll(){
            return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
        }

        @GetMapping("/client/{idClient}")
        @Operation(
                summary = "Get purchases by client ID",
                description = "Returns a list of purchases made by a specific client"
        )
        @ApiResponse(responseCode = "200", description = "Purchases found for the client")
        @ApiResponse(responseCode = "404", description = "No purchases found for the client")
        @ApiResponse(responseCode = "500", description = "Internal server error")
        public ResponseEntity<List<Purchase>> getByClient(
                @Parameter(description = "Client ID", example = "123456", required = true)
                @PathVariable("idClient") String clientId){
            return purchaseService.getByClient(clientId).map(
                    purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
            ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping("/save")
        @Operation(
                summary = "Save a new purchase",
                description = "Registers a new purchase and returns the created record",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        required = true,
                        content = @Content(
                                examples = @ExampleObject(
                                        name = "Example Purchase",
                                        value = """
                                                {
                                                  "clientId": "4546221",
                                                  "date": "2023-08-10T17:30:00",
                                                  "paymentMethod": "E",
                                                  "comment": "Compra de prueba desde Swagger",
                                                  "state": "P",
                                                  "items": [
                                                    {
                                                      "productId": 1,
                                                      "quantity": 6,
                                                      "total": 250.00,
                                                      "active": true
                                                    }
                                                  ]
                                                }
                                        """
                                )
                        )
                )
        )
        @ApiResponse(responseCode = "201", description = "Purchase created successfully")
        @ApiResponse(responseCode = "400", description = "Invalid purchase data")
        @ApiResponse(responseCode = "500", description = "Internal server error")
        public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
            return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
        }

    }
