package com.tecdesoftware.market.persistence.mapper;

import com.tecdesoftware.market.domain.PurchaseItem;
import com.tecdesoftware.market.persistence.entity.CompraProducto;
import com.tecdesoftware.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses={ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target="productId"),
            @Mapping(source = "cantidad", target="quantity"),
            @Mapping(source = "estado", target="active")
            // el de total no es necesario mapearlo, ya que ambos se llaman igual
    })
    PurchaseItem toPurchaseItem(CompraProducto producto);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "productId", target = "id.idProducto"), // Mapea el id del producto a la llave compuesta
            @Mapping(target="id.idCompra", ignore=true),              // Este se asigna después, por eso se ignora
            @Mapping(target="compra", ignore=true),                   // Se asigna manualmente en el servicio/repository
            // Es obligatorio asignar un objeto Producto no nulo para que Hibernate pueda obtener el ID
            @Mapping(target="producto", expression = "java(createProductoFromId(item.getProductId()))")
    })
    CompraProducto toCompraProducto(PurchaseItem item);

    /**
     * Método helper para crear un objeto Producto con solo el ID asignado.
     * Esto es necesario porque Hibernate usa la referencia a Producto
     * para obtener la clave compuesta de CompraProducto.
     *
     * No se crea un nuevo producto en la base de datos, solo se asigna
     * la referencia al producto existente con ese ID.
     */
    default Producto createProductoFromId(int productId) {
        Producto producto = new Producto();
        producto.setIdProducto(productId);
        return producto;
    }
}
