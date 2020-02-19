package com.moduleTesting.portal.repository;

import com.moduleTesting.portal.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findById(Integer brandId);
}
