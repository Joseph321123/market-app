package com.tecdesoftware.market.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tecdesoftware.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    //query method
    //select *
    // FROM  productos
    //WHERE id_categoria =5 ?
   //Order by nombre ASC
    List<Producto> findByIdcategoriaOrderByNombre(int idCategoria);

    //cantidad en stock
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);


}