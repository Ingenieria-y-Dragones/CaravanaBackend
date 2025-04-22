package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.ProductoDTO;
import co.edu.javeriana.caravana.model.Producto;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setTipo(producto.getTipo());
        return productoDTO;
    }

    public static Producto toEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setTipo(productoDTO.getTipo());
        return producto;
    }
}
