package com.idruide.backend.packingservice.mapper;

import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.entities.Packing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Thierry Kwekam
 */

@Mapper
public interface PackingMapper {

    @Mapping(source = "packingDto.createdAt", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "packingDto.deliverDate", target = "deliverDate", dateFormat = "dd-MM-yyyy HH:mm")
    Packing toPacking(PackingDto packingDto);

    @Mapping(source = "packing.order.orderNumber", target = "orderNumber")
    PackingDto toPackingDto(Packing packing);

    default List<PackingDto> toPackingsDto(List<Packing> packing) {
        return Optional.ofNullable(packing)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toPackingDto)
                .collect(Collectors.toList());

    }
}
