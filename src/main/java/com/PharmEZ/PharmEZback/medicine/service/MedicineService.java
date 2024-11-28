package com.PharmEZ.PharmEZback.medicine.service;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import org.springframework.stereotype.Service;

public interface MedicineService {

    /**
     * find medicine by Id
     *
     * @author sylee
     */
    MedicineInfoResponse findMedicineById(Long id);

//    /**
//     * 재고가 있는 약을 보여주는 메소드입니다.
//     */
//    List<MedicinListResponse> findMedeicineByName(String medicineName);

}