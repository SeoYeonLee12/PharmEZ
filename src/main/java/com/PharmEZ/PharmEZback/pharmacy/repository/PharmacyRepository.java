package com.PharmEZ.PharmEZback.pharmacy.repository;

import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long>, PharmacyRepositoryCustom {
}
