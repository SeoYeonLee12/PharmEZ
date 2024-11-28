package com.PharmEZ.PharmEZback.medicine.service.impl;

import static com.PharmEZ.PharmEZback.medicine.utility.MedicineMapper.toMedicineInfoResponse;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import com.PharmEZ.PharmEZback.medicine.repository.MedicineRepository;
import com.PharmEZ.PharmEZback.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    /**
     * find medicine by Id
     *
     * @param id
     * @author sylee
     */
    @Override
    public MedicineInfoResponse findMedicineById(Long id) {
        MedicineInfoResponse response = toMedicineInfoResponse(medicineRepository.findById(id).orElse(null));
        return response;
    }
}
