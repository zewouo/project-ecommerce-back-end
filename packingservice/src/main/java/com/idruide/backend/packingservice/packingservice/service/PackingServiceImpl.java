package com.idruide.backend.packingservice.packingservice.service;

import com.idruide.backend.packingservice.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.packingservice.exception.PackingNotFoundException;
import com.idruide.backend.packingservice.packingservice.entities.Packing;
import com.idruide.backend.packingservice.packingservice.mapper.OrderMapper;
import com.idruide.backend.packingservice.packingservice.mapper.PackingMapper;
import com.idruide.backend.packingservice.packingservice.repository.PackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Service
public class PackingServiceImpl implements PackingService {

    @Autowired
    private PackingRepository packingRepository;

    @Autowired
    private PackingMapper packingMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<PackingDto> getAllPackings() {return packingMapper.toPackingsDto(packingRepository.findAll());}

    @Override
    public PackingDto validateAndGetPackingById(Integer id){
        return packingMapper.toPackingDto(packingRepository.findById(id).orElseThrow(()
                -> new PackingNotFoundException("Order not found", id)));
    }

    @Override
    public PackingDto savePacking(PackingDto packingDto) {
        Packing packing = packingMapper.toPacking(packingDto);
        packingRepository.save(packing);
        return packingDto;
    }

    @Override
    public void deletePacking(PackingDto packingDto){ packingRepository.delete(packingMapper.toPacking(packingDto));}


}
