package com.springframework.mappers;

import com.springframework.entities.Beer;
import com.springframework.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDto);

    BeerDTO beerToBeerDto(Beer beer);

}
