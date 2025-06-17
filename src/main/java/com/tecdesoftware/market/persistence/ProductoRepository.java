package com.tecdesoftware.market.persistence;

package persistence;
import java.util.List;

public class ProductoRepository {
    private ProductosCrudRepository productosCrudRepository;

    //Deberia de ser solo List<Producto>
    public List<com.tecdesoftware.market.persistence.entity.Producto> findAll() {
        return (List<com.tecdesoftware.market.persistence.entity.Producto>) productos;
    }
}
