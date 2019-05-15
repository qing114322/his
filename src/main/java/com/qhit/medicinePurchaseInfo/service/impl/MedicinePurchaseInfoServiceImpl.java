package com.qhit.medicinePurchaseInfo.service.impl;

import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;
import java.util.List;
import com.qhit.medicinePurchaseInfo.dao.IMedicinePurchaseInfoDao;
import com.qhit.medicinePurchaseInfo.dao.impl.MedicinePurchaseInfoDaoImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;

/**
* Created by GeneratorCode on 2018/12/03
*/

public class MedicinePurchaseInfoServiceImpl  implements IMedicinePurchaseInfoService {

    IMedicinePurchaseInfoDao dao = new MedicinePurchaseInfoDaoImpl();

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
        MedicinePurchaseInfo medicinePurchaseInfo = findById(id); 
        return dao.delete(medicinePurchaseInfo); 
    } 


    @Override 
    public List findAll() {
        String sql="SELECT * from medicine_purchase_info m JOIN base_user b ON m.MAN_CODE=b.user_id";
        List<MedicinePurchaseInfo> list = dao.freeFind(sql);
        return list;
    } 


    @Override 
    public MedicinePurchaseInfo findById(Object id) { 
        List<MedicinePurchaseInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicinePurchaseInfo> search(MedicinePurchaseInfo medicinePurchaseInfo) {
            String sql = "select * from medicine_purchase_info where 1=1 "; 
            if (medicinePurchaseInfo.getMedicineCodeid()!=null && !"".equals(medicinePurchaseInfo.getMedicineCodeid())){        
                sql+=" and MEDICINE_CODEID like '%"+medicinePurchaseInfo.getMedicineCodeid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getManCode()!=null && !"".equals(medicinePurchaseInfo.getManCode())){        
                sql+=" and MAN_CODE like '%"+medicinePurchaseInfo.getManCode()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchAmt()!=null && !"".equals(medicinePurchaseInfo.getPchAmt())){        
                sql+=" and PCH_AMT like '%"+medicinePurchaseInfo.getPchAmt()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchPrice()!=null && !"".equals(medicinePurchaseInfo.getPchPrice())){        
                sql+=" and PCH_PRICE like '%"+medicinePurchaseInfo.getPchPrice()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchTotal()!=null && !"".equals(medicinePurchaseInfo.getPchTotal())){        
                sql+=" and PCH_TOTAL like '%"+medicinePurchaseInfo.getPchTotal()+"%' ";        
            } 
            if (medicinePurchaseInfo.getStatus()!=null && !"".equals(medicinePurchaseInfo.getStatus())){        
                sql+=" and STATUS like '%"+medicinePurchaseInfo.getStatus()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchUserid()!=null && !"".equals(medicinePurchaseInfo.getPchUserid())){        
                sql+=" and PCH_USERID like '%"+medicinePurchaseInfo.getPchUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchDate()!=null && !"".equals(medicinePurchaseInfo.getPchDate())){        
                sql+=" and PCH_DATE like '%"+medicinePurchaseInfo.getPchDate()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvUserid()!=null && !"".equals(medicinePurchaseInfo.getApprvUserid())){        
                sql+=" and APPRV_USERID like '%"+medicinePurchaseInfo.getApprvUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvDate()!=null && !"".equals(medicinePurchaseInfo.getApprvDate())){        
                sql+=" and APPRV_DATE like '%"+medicinePurchaseInfo.getApprvDate()+"%' ";        
            } 
            List<MedicinePurchaseInfo> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<MedicinePurchaseInfo> findall() {
        String sql="SELECT CAST(SUM(mrp.REQAMT)AS CHAR)AS sumreqamt,CAST(SUM(mc.retail_price*mrp.PURPOSE)AS CHAR)AS sumtotal \n" +
                "from medicine_code mc\n" +
                "JOIN medicine_req_plan mrp  ON mc.code_id=mrp.MEDICINE_CODEID\n" +
                "JOIN base_user bu ON bu.user_id=mrp.APPRV_USERID\n" +
                "LEFT JOIN medicine_purchase_info p ON p.PCH_ID=mrp.REQPLNNO\n" +
                "WHERE  mrp.STATUS=2\n" +
                "GROUP BY mrp.MEDICINE_CODEID";
        List<MedicinePurchaseInfo> list = dao.freeFind(sql);
        return list;
    }


}