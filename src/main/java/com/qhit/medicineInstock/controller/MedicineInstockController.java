package com.qhit.medicineInstock.controller; 

import com.qhit.medicineCode.pojo.MedicineCode;
import com.qhit.medicineCode.service.IMedicineCodeService;
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import com.qhit.medicineInstock.pojo.MedicineInstock;
import com.qhit.medicineInstock.service.IMedicineInstockService; 
import com.qhit.medicineInstock.service.impl.MedicineInstockServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService;
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl;
import com.qhit.medicineStockinfo.pojo.MedicineStockinfo;
import com.qhit.medicineStockinfo.service.IMedicineStockinfoService;
import com.qhit.medicineStockinfo.service.impl.MedicineStockinfoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/08
*/
@Controller 
@RequestMapping("/medicineInstock") 
public class MedicineInstockController { 

    IMedicineInstockService medicineInstockService = new MedicineInstockServiceImpl();;
    IMedicineCodeService medicineCodeService = new MedicineCodeServiceImpl();

    @RequestMapping("/insert") 
    public String insert(MedicineInstock medicineInstock) { 
        medicineInstockService.insert(medicineInstock); 
        return "forward:list.action"; 
    }
    @RequestMapping("/insertCon")
    public String insertCon(String instockId ,String invno ,Model model) {
        //查询总数据
        List<MedicineCode> list = medicineCodeService.findall5();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        MedicineInstock medicineInstock=new MedicineInstock();
        //遍历获取入库信息的id
        String[] split = instockId.split(",");
        for(String s:split){
//            private String invno;    //发票号
//            private Integer medicineCodeid;    //药品
//            private Integer inamt;    //入库数量
//            private Double unitprc;    //入库单价
//            private Double zje;    //入库总金额
//            private Integer instockUserid;    //入库人
//            private String instockDate;    //入库日期
//            private Integer manCode;    //供应商
//            for(MedicineCode stats:list){
//                stats.getMedicineReqPlan().setStatus(4);
//            }
            //通过id查找要修改的状态对象
//            MedicineCode medicineCodestats=medicineCodeService.findbyid(s);
//            medicineCodestats.getMedicineReqPlan().setStatus(4);
//            medicineCodeService.update(medicineCodestats);
           MedicineCode medicineCode=medicineCodeService.finall(s);
           //插入药品id
           medicineInstock.setMedicineCodeid(Integer.valueOf(s));
           //入库数量
            medicineInstock.setInamt(Integer.valueOf(medicineCode.getSumreqamt()));
            //入库单价
            medicineInstock.setUnitprc(Double.valueOf(medicineCode.getRetailPrice()));
            //入库总金额
            medicineInstock.setZje(Double.valueOf(medicineCode.getSumtotal()));
            //入库人
            medicineInstock.setInstockUserid(medicineCode.getBaseUser().getUserId());
            //入库日期
            medicineInstock.setInstockDate(format);
            //供应商
            medicineInstock.setManCode(Integer.valueOf(medicineCode.getManCode()));
            medicineInstockService.insert(medicineInstock);



            //遍历获取发票号
            String[] split1 = invno.split(",");
            for(String s1:split1){
                //发票号
                if("".equals(s1)){

                }else {
                    MedicineInstock byId = medicineInstockService.findByid(s);
                    byId.setInvno(s1);
                    medicineInstockService.updateSelective(byId);
                }


            }
            //给药品需求修改状态为4（已入库）
            IMedicineReqPlanService medicineReqPlanService = new MedicineReqPlanServiceImpl();
            //根据入库药品id修改状态
            MedicineCode medicineCode1= medicineCodeService.findbcodeid(String.valueOf(s));


//            MedicineReqPlan medicineReqPlan= medicineReqPlanService.findbcodeidstats(medicineCode1.getCodeId());
//            medicineReqPlan.setStatus(4);
//            medicineReqPlanService.updateSelective(medicineReqPlan);
//            System.out.println(codeid.getMedicineCodeid());
//            medicineReqPlanService.updateSelective(medicineReqPlan);

        }


        // 更新库存表
        IMedicineStockinfoService medicineStockinfoService = new MedicineStockinfoServiceImpl();
        //通过连表查找需要数据的集合
        List<MedicineCode>list1= medicineCodeService.findall5statas4();
        //查询库存表中的数据
        List<MedicineStockinfo> medicineStockinfoServiceAll = medicineStockinfoService.findAll();
        //创建库存表对象方便插入数据
        MedicineStockinfo medicineStockinfo=new MedicineStockinfo();
        //获取要入库的id
        boolean flag=true;
        for (MedicineCode codeid:list1){
                    //库存表中没有药品开始插入
//                    private Integer stockinfoId;    //主键
//                    private Integer medicinecodeId;    //药品
//                    private Integer amt;    //库存数量
//                    private Double unitprc;    //库存单价
//                    private Double saleunitprc;    //销售单价(库存单价*1.5)
//                    private Double zje;    //库存总金额(库存单价*数量)
                    //药品
                    medicineStockinfo.setMedicinecodeId(codeid.getCodeId());
                    //库存数量
                    medicineStockinfo.setAmt(Integer.valueOf(codeid.getSumreqamt()));
                    //库存单价
                    medicineStockinfo.setUnitprc(Double.valueOf(codeid.getRetailPrice()));
                    //销售单价(库存单价*1.5)
                    medicineStockinfo.setUnitprc(Double.valueOf(codeid.getRetailPrice())*1);
                    //库存总金额(库存单价*数量)
                    medicineStockinfo.setZje(Double.valueOf(codeid.getRetailPrice())*Integer.valueOf(codeid.getSumreqamt()));
                    medicineStockinfoService.insert(medicineStockinfo);
//                System.out.println(medicine_codeid.getMedicinecodeId());

//            System.out.println(codeid.getCodeId());
        }







//        String s1 = JSON.toJSONString(medicineInstock.getMedicineCodeid());
//        System.out.println(s1);



        model.addAttribute("list",list);
        return "forward:list2.action";
//        return "medicineInstock/list";
    }
 
    @RequestMapping("/delete") 
    public String delete(Integer instockId, HttpServletResponse response) throws IOException { 
        medicineInstockService.delete(instockId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicineInstock medicineInstock) { 
        medicineInstockService.update(medicineInstock); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicineInstock medicineInstock) { 
        medicineInstockService.updateSelective(medicineInstock); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer instockId, Model model) { 
        MedicineInstock medicineInstock = medicineInstockService.findById(instockId); 
        model.addAttribute("medicineInstock",medicineInstock); 
        return "medicineInstock/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicineInstock> list = medicineInstockService.findAll(); 
        model.addAttribute("list",list); 
        return "medicineInstock/list"; 
    }
    @RequestMapping("/list2")
    public String list2(Model model) throws IOException {
        List<MedicineCode> list = medicineCodeService.findall2();
        model.addAttribute("list",list);
        return "medicineInstock/list";
    }
    @RequestMapping("/listCon")
    public String listCon(Model model) throws IOException {
        IMedicineCodeService medicineCodeService = new MedicineCodeServiceImpl();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
        List<MedicineCode> list = medicineCodeService.findall5();
//        for(MedicineCode s :list){
//            s.getMedicineReqPlan().setStatus(3);
//        }
        String s = JSON.toJSONString(list);
        System.out.println(s);
//        model.addAttribute("list",list);
//        model.addAttribute("format",format);
//        List<MedicineInstock> list = medicineInstockService.findAll();
        model.addAttribute("list",list);
        return "medicineInstock/list";
    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicineInstock> list = medicineInstockService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicineInstock medicineInstock,Model model) { 
        List<MedicineInstock> list = medicineInstockService.search(medicineInstock); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicineInstock); 
        return "medicineInstock/list"; 
    } 
 
} 
