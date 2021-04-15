package com.idruide.backend.packingservice.service;

import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.dto.ProductDto;
import com.idruide.backend.packingservice.entities.Order;
import com.idruide.backend.packingservice.entities.OrderProduct;
import com.idruide.backend.packingservice.entities.Packing;
import com.idruide.backend.packingservice.entities.Product;
import com.idruide.backend.packingservice.exception.PackingNotFoundException;
import com.idruide.backend.packingservice.mapper.PackingMapper;
import com.idruide.backend.packingservice.mapper.ProductMapper;
import com.idruide.backend.packingservice.repository.OrderRepository;
import com.idruide.backend.packingservice.repository.PackingRepository;
import com.idruide.backend.packingservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Thierry Kwekam
 */
@Service
@Slf4j
public class PackingServiceImpl implements PackingService {


    private final PackingRepository packingRepository;

    private final PackingMapper packingMapper;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public PackingServiceImpl(PackingRepository packingRepository,
                              PackingMapper packingMapper,
                              OrderRepository orderRepository,
                              ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.packingRepository = packingRepository;
        this.packingMapper = packingMapper;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    @Transactional
    public List<PackingDto> getAllPackings() {
        return this.packingMapper.toPackingsDto(packingRepository.findAll());
    }

    @Override
    @Transactional
    public PackingDto getPackingByCode(String codePacking) {
        if (codePacking == null) {
            return null;
        }
        return this.packingRepository.findByCodePacking(codePacking)
                .stream()
                .map(order -> this.packingMapper.toPackingDto(order))
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByPacking(String codePacking) {
        List<Product> products = new ArrayList<Product>();
        List<Product> results = new ArrayList<Product>();
        if (codePacking != null) {
            PackingDto pack = getPackingByCode(codePacking);
            if (pack.getOrderNumber() != null) {
                Order order = this.orderRepository.findByOrderNumber(pack.getOrderNumber());
                List<OrderProduct> orderProducts = order.getOrderProducts();

                for (OrderProduct orderProduct : orderProducts) {
                    Product prod = orderProduct.getProduct();
                    Integer quantity = orderProduct.getQuantity();
                    prod.setQuantity(quantity);
                    products.add(prod);
                }
                //to split in order to have one product in the  command
                // and several product in the packing
                for (Product prod : products) {
                    int nbrProduct = prod.getQuantity();
                    if (nbrProduct > 1) {
                        for (int i = 0; i < nbrProduct; i++) {
                            Product pro = Product.builder()
                                    .codeProduct(prod.getCodeProduct())
                                    .description(prod.getDescription())
                                    .name(prod.getName())
                                    .price(prod.getPrice())
                                    .productId(prod.getProductId())
                                    .quantity(1)
                                    .build();
                            results.add(pro);
                        }

                    }
                }
            }
        }
        return this.productMapper.toProductsDto(results);
    }

    @Override
    @Transactional
    public PackingDto savePacking(PackingDto packingDto) {
        Order order = this.orderRepository.findByOrderNumber(packingDto.getOrderNumber());
        Packing packing = this.packingMapper.toPacking(packingDto);
        packing.setOrder(order);
        return this.packingMapper.toPackingDto(this.packingRepository.save(packing));
    }

    @Override
    @Transactional
    public PackingDto deletePacking(PackingDto packingDto) {
        return Optional.ofNullable(packingDto.getCodePacking())
                .filter(StringUtils::isNotBlank)
                .map(this.packingRepository::findByCodePacking)
                .map(packing -> {
                    if (packing.isEmpty()) {
                        return null;
                    } else return packing;
                })
                .map(List::stream)
                .get()
                .map(packing -> {
                    if (packing != null) this.packingRepository.delete(packing);
                    return this.packingMapper.toPackingDto(packing);
                })
                .findFirst().orElseThrow(() -> new PackingNotFoundException("packing code not found. ", -1));
    }

}
