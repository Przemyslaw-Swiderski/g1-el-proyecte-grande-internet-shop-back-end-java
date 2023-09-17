package com.g1elproyectegrande.mapper;

import com.g1elproyectegrande.controller.dto.ProductProducerDto;
import com.g1elproyectegrande.entity.ProductProducer;
import org.springframework.stereotype.Component;

@Component
public class ProductProducerMapper {
    public ProductProducerDto mapProductProducerEntityToDto (ProductProducer entity){
        return new ProductProducerDto(
        entity.getId(),
        entity.getName()
        );
    }

    public ProductProducer mapProductProducerDtoToEntity (ProductProducerDto dto){
        return new ProductProducer(
                dto.id(),
                dto.name()
        );
    }
}
