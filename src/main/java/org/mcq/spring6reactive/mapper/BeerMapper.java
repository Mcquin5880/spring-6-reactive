package org.mcq.spring6reactive.mapper;

import org.mapstruct.Mapper;
import org.mcq.spring6reactive.domain.Beer;
import org.mcq.spring6reactive.model.BeerDTO;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
