package com.moduleTesting.portal.service.brand.impl;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.repository.BrandRepository;
import com.moduleTesting.portal.service.brand.BrandService;
import com.moduleTesting.portal.service.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream().map(DtoMapper::toBrandDto).collect(Collectors.toList());
    }
}
