package com.PharmEZ.PharmEZback.medicine.service.impl;

import static com.PharmEZ.PharmEZback.medicine.utility.MedicineMapper.toMedicineInfoResponse;
import static com.PharmEZ.PharmEZback.medicine.utility.MedicineMapper.toMedicineListResponses;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineListResponse;
import com.PharmEZ.PharmEZback.medicine.repository.MedicineRepository;
import com.PharmEZ.PharmEZback.medicine.service.MedicineService;
import java.util.List;
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

    /**
     * 약명을 검색한 결과를 보여주는 메소드입니다.
     *
     * @param medicineName
     * @author sylee
     */
    @Override
    public List<MedicineListResponse> findByMedicineNameContaining(String medicineName) {

        return toMedicineListResponses(medicineRepository.findByMedicineNameContaining(medicineName));
    }

    /**
     * 모든 약을 보여주는 메소드입니다.
     *
     * @author sylee
     */
    @Override
    public List<MedicineListResponse> findAllMedicine() {
        return toMedicineListResponses(medicineRepository.findAll());
    }
}
