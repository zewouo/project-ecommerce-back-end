package com.idruide.backend.packingservice.packingservice.mapper;

import com.idruide.backend.packingservice.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.packingservice.entities.Packing;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Mapper(builder = @Builder(disableBuilder = true), uses = {OrderMapper.class})
public interface PackingMapper{

   @Mapping(source = "packingDto.order", target = "order", qualifiedByName = "toOrder")
    public Packing toPacking(PackingDto packingDto);

    public PackingDto toPackingDto(Packing packing);

    default List<PackingDto> toPackingsDto(List<Packing> packing) {
        return Optional.ofNullable(packing)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toPackingDto)
                .collect(Collectors.toList());

    }
}
