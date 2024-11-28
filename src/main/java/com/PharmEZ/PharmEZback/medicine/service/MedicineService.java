package com.PharmEZ.PharmEZback.medicine.service;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineListResponse;
import java.util.List;
import org.springframework.stereotype.Service;

public interface MedicineService {

    /**
     * find medicine by Id
     *
     * @author sylee
     */
    MedicineInfoResponse findMedicineById(Long id);

    /**
     * 약명으로 검색한 결과를 보여주는 메소드입니다.
     *
     * @author sylee
     */
    List<MedicineListResponse> findByMedicineNameContaining(String medicineName);

}