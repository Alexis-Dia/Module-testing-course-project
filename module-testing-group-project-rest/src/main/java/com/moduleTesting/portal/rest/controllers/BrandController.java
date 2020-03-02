package com.moduleTesting.portal.rest.controllers;

import com.moduleTesting.portal.dto.BrandDto;
import com.moduleTesting.portal.service.brand.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/all")
    public List<BrandDto> getAllBrands() {

        final List<BrandDto> allBrands = brandService.findAll();

        return allBrands;
    }
}
