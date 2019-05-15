package com.qhit.medicineCode.service.impl;

import com.qhit.medicineCode.service.IMedicineCodeService;
import java.util.List;
import com.qhit.medicineCode.dao.IMedicineCodeDao;
import com.qhit.medicineCode.dao.impl.MedicineCodeDaoImpl;
import com.qhit.medicineCode.pojo.MedicineCode;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;

/**
* Created by GeneratorCode on 2018/12/03
*/

public class MedicineCodeServiceImpl  implements IMedicineCodeService {

    IMedicineCodeDao dao = new MedicineCodeDaoImpl();

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 


    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 


    @Override 
    public boolean delete(Object id) { 
        MedicineCode medicineCode = findById(id); 
        return dao.delete(medicineCode); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public MedicineCode findById(Object id) { 
        List<MedicineCode> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicineCode> search(MedicineCode medicineCode) {
            String sql = "select * from medicine_code where 1=1 "; 
            if (medicineCode.getMedicineName()!=null && !"".equals(medicineCode.getMedicineName())){        
                sql+=" and MEDICINE_NAME like '%"+medicineCode.getMedicineName()+"%' ";        
            } 
            if (medicineCode.getAliasName()!=null && !"".equals(medicineCode.getAliasName())){        
                sql+=" and alias_name like '%"+medicineCode.getAliasName()+"%' ";        
            } 
            if (medicineCode.getSpecification()!=null && !"".equals(medicineCode.getSpecification())){        
                sql+=" and specification like '%"+medicineCode.getSpecification()+"%' ";        
            } 
            if (medicineCode.getManCode()!=null && !"".equals(medicineCode.getManCode())){        
                sql+=" and man_code like '%"+medicineCode.getManCode()+"%' ";        
            } 
            if (medicineCode.getManChnName()!=null && !"".equals(medicineCode.getManChnName())){        
                sql+=" and man_chn_name like '%"+medicineCode.getManChnName()+"%' ";        
            } 
            if (medicineCode.getTypeName()!=null && !"".equals(medicineCode.getTypeName())){        
                sql+=" and type_name like '%"+medicineCode.getTypeName()+"%' ";        
            } 
            if (medicineCode.getTypeCode()!=null && !"".equals(medicineCode.getTypeCode())){        
                sql+=" and type_code like '%"+medicineCode.getTypeCode()+"%' ";        
            } 
            if (medicineCode.getStockUnit()!=null && !"".equals(medicineCode.getStockUnit())){        
                sql+=" and stock_unit like '%"+medicineCode.getStockUnit()+"%' ";        
            } 
            if (medicineCode.getRetailPrice()!=null && !"".equals(medicineCode.getRetailPrice())){        
                sql+=" and retail_price like '%"+medicineCode.getRetailPrice()+"%' ";        
            } 
            if (medicineCode.getStockPrice()!=null && !"".equals(medicineCode.getStockPrice())){        
                sql+=" and stock_price like '%"+medicineCode.getStockPrice()+"%' ";        
            } 
            if (medicineCode.getDrugNotesPatient()!=null && !"".equals(medicineCode.getDrugNotesPatient())){        
                sql+=" and drug_notes_patient like '%"+medicineCode.getDrugNotesPatient()+"%' ";        
            } 
            if (medicineCode.getDrugNote()!=null && !"".equals(medicineCode.getDrugNote())){        
                sql+=" and drug_note like '%"+medicineCode.getDrugNote()+"%' ";        
            } 
            if (medicineCode.getDrugForm()!=null && !"".equals(medicineCode.getDrugForm())){        
                sql+=" and drug_form like '%"+medicineCode.getDrugForm()+"%' ";        
            } 
            List<MedicineCode> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<MedicineCode> findall(String format) {
        IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();
        String sql="SELECT *, CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal \n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "WHERE  mrp.STATUS=2\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        List<MedicineCode> list = dao.freeFind(sql);
        MedicinePurchaseInfo medicinePurchaseInfo=new MedicinePurchaseInfo();
        for(MedicineCode code:list){
            //药品id
            medicinePurchaseInfo.setMedicineCodeid(code.getCodeId());
            //供应商
            medicinePurchaseInfo.setManCode(Integer.valueOf(code.getManCode()));
            //采购数量
            medicinePurchaseInfo.setPchAmt(code.getMedicineReqPlan().getReqamt());
            //采购单价
            medicinePurchaseInfo.setPchPrice(Double.valueOf(code.getRetailPrice()));
            //采购总价
            medicinePurchaseInfo.setPchTotal(Double.valueOf(code.getSumtotal()));
            //状态
            medicinePurchaseInfo.setStatus(3);
            //汇总人
            medicinePurchaseInfo.setPchUserid(code.getBaseUser().getUserId());
            //汇总日期
            medicinePurchaseInfo.setPchDate(format);
            //审批人
            medicinePurchaseInfo.setApprvUserid(code.getMedicineReqPlan().getApprvUserid());
            //审批日期
            medicinePurchaseInfo.setApprvDate(code.getMedicineReqPlan().getApprvDate());
            medicinePurchaseInfoService.insert(medicinePurchaseInfo);
        }
        return list;
    }

    @Override
    public MedicineCode finall(String s) {
        String sql="SELECT  *,CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal\n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "WHERE  mrp.STATUS=2\n" +
                "AND mc.code_id='"+s+"'\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
       List<MedicineCode>list= dao.freeFind(sql);
        return list.get(0);
    }

    @Override
    public List<MedicineCode> findall5() {
        String sql="SELECT  *,CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal\n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "LEFT JOIN medicine_instock mi ON mi.medicine_codeid=mc.code_id\n" +
                "WHERE  mrp.STATUS=2\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        List<MedicineCode> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<MedicineCode> findall5statas4() {
        String sql="SELECT  *,CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal\n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "LEFT JOIN medicine_instock mi ON mi.medicine_codeid=mc.code_id\n" +
                "WHERE  mrp.STATUS=4\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        return dao.freeFind(sql);
    }

    @Override
    public MedicineCode findbyid(String s) {
        String sql="SELECT  *,CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal\n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "LEFT JOIN medicine_instock mi ON mi.medicine_codeid=mc.code_id\n" +
                "WHERE  mrp.STATUS=2\n" +
                "AND mc.code_id='"+s+"'\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        List<MedicineCode> list = dao.freeFind(sql);


        return list.get(0);
    }

    @Override
    public MedicineCode findbcodeid(String s) {
        String  sql="SELECT  *\n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "LEFT JOIN medicine_instock mi ON mi.medicine_codeid=mc.code_id\n" +
                "WHERE  mrp.STATUS=2 " +
                "AND mrp.MEDICINE_CODEID='"+s+"' \n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        List<MedicineCode> list = dao.freeFind(sql);
        return list.get(0);
    }

    @Override
    public MedicineCode findbcodeidstats(Integer codeId) {
        String sql2="SELECT *from medicine_req_plan WHERE medicine_codeid='"+codeId+"'";
        List<MedicineCode> list = dao.freeFind(sql2);
        return list.get(0);
    }

    @Override
    public List<MedicineCode> findall2() {
        IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();
        String sql="SELECT *, CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal \n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "WHERE  mrp.STATUS=2\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        List<MedicineCode> list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<MedicineCode> findajaxListRight(Integer menuId) {
        String sql="SELECT * from medicine_code mc JOIN doctor_menu_medicine dmm ON mc.code_id=dmm.code_id\n" +
                "WHERE mc.code_id NOT IN(\n" +
                "SELECT dmm.code_id \n" +
                "FROM doctor_menu_medicine dmm JOIN medicine_code mc ON dmm.code_id=mc.code_id\n" +
                "WHERE dmm.menu_id='"+menuId+"');";
        List<MedicineCode> list = dao.freeFind(sql);
        return list;
    }


}