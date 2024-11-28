package com.PharmEZ.PharmEZback.medicine.repository;

import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sylee
 */
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByMedicineNameContaining(String keyword);

}
