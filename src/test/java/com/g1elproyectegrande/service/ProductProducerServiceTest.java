package com.g1elproyectegrande.service;

import com.g1elproyectegrande.controller.dto.ProductProducerDto;
import com.g1elproyectegrande.entity.ProductProducer;
import com.g1elproyectegrande.mapper.ProductProducerMapper;
import com.g1elproyectegrande.repository.ProductProducerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductProducerServiceTest {
    @Mock
    private ProductProducerRepository productProducerRepository;
    @Mock
    private ProductProducerMapper productProducerMapper;

    @InjectMocks
    private ProductProducerService productProducerService;

    @Test
    void shouldReturnEmptyListWhenNoProductProducersAreInDb (){
        //Given
        Mockito.when(productProducerRepository.findAll()).thenReturn(List.of());

        //When
        List<ProductProducerDto> returnedListOfAllProductsProducersTested = productProducerService.getAllProductsProducers();

        //Then
        Assertions.assertThat(returnedListOfAllProductsProducersTested).isEmpty();
    }


    @Test
    void shouldReturnOneProductsProducerFromDb (){
        ProductProducer productProducer = new ProductProducer(1L, "SCOTT");
        ProductProducerDto productProducerDto = new ProductProducerDto( 1L, "SCOTT");

        //Given
        Mockito.when(productProducerRepository.findAll()).thenReturn(List.of(productProducer));
        Mockito.when(productProducerMapper.mapProductProducerEntityToDto(productProducer)).thenReturn(productProducerDto);

        //When
        List<ProductProducerDto> returnedListOfAllProductsProducersTested = productProducerService.getAllProductsProducers();

        //Then
        Assertions.assertThat(returnedListOfAllProductsProducersTested.size()).isEqualTo(1);
        Assertions.assertThat(returnedListOfAllProductsProducersTested.get(0)).isEqualTo(productProducerDto);

    }

}