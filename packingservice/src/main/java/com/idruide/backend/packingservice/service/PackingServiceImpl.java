package com.idruide.backend.packingservice.service;

import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.entities.Order;
import com.idruide.backend.packingservice.entities.Packing;
import com.idruide.backend.packingservice.exception.PackingNotFoundException;
import com.idruide.backend.packingservice.mapper.PackingMapper;
import com.idruide.backend.packingservice.repository.OrderRepository;
import com.idruide.backend.packingservice.repository.PackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Service
public class PackingServiceImpl implements PackingService {


    private PackingRepository packingRepository;

    private PackingMapper packingMapper;

    private OrderRepository orderRepository;

    @Autowired
    public PackingServiceImpl(PackingRepository packingRepository, PackingMapper packingMapper, OrderRepository orderRepository) {
        this.packingRepository = packingRepository;
        this.packingMapper = packingMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public List<PackingDto> getAllPackings() {return this.packingMapper.toPackingsDto(packingRepository.findAll());}

    @Override
    @Transactional
    public PackingDto validateAndGetPackingById(Integer id){
        return this.packingMapper.toPackingDto(this.packingRepository.findById(id).orElseThrow(()
                -> new PackingNotFoundException("Order not found", id)));
    }

    @Override
    @Transactional
    public PackingDto savePacking(PackingDto packingDto) {
        Order order = this.orderRepository.getOne(packingDto.getOrderId());
        Packing packing = this.packingMapper.toPacking(packingDto);
        packing.setOrder(order);
        return this.packingMapper.toPackingDto(this.packingRepository.save(packing));
    }

    @Override
    @Transactional
    public void deletePacking(PackingDto packingDto){ this.packingRepository.delete(this.packingMapper.toPacking(packingDto));}


}
