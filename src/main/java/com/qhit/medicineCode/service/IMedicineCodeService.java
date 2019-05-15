package com.qhit.medicineCode.service;

import java.util.List;
import com.qhit.medicineCode.pojo.MedicineCode;
/**
* Created by GeneratorCode on 2018/12/03
*/

public interface IMedicineCodeService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    MedicineCode findById(Object id);

    boolean freeUpdate(String sql);

    List<MedicineCode> search(MedicineCode medicineCode);

    List<MedicineCode> findall(String format);

    MedicineCode finall(String s);

    List<MedicineCode> findall5();

    List<MedicineCode> findall5statas4();

    MedicineCode findbyid(String s);

    MedicineCode findbcodeid(String s);

    MedicineCode findbcodeidstats(Integer codeId);

    List<MedicineCode> findall2();

    List<MedicineCode> findajaxListRight(Integer menuId);
}