package com.PharmEZ.PharmEZback.stock.repository;

import com.PharmEZ.PharmEZback.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom {
}
