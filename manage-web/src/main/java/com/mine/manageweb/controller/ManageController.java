package com.mine.manageweb.controller;

import com.mine.bean.*;
import com.mine.service.ManageService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/3/26 14:15
 **/
@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;

    @GetMapping("getCatalog1")
    public List<BaseCatalog1> getBaseCatalog1(){
        return manageService.getCatalog1();
    }

    @GetMapping("getCatalog2")
    public List<BaseCatalog2> getBaseCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);
    }

    @GetMapping("getCatalog3")
    public List<BaseCatalog3> getBaseCatalog3(String catalog2Id){
        return manageService.getCatalog3(catalog2Id);
    }

    @GetMapping("attrInfoList")
    public List<BaseAttrInfo> getBaseAttrInfo(String catalog3Id){
        return manageService.getBaseAttrInfo(catalog3Id);
    }

    @GetMapping("attrValueList")
    public List<BaseAttrValue> getBaseAttrValue(String attrInfoId){
        return manageService.getBaseAttrValue(attrInfoId);
    }

    @PostMapping("saveBaseAttr")
    public String saveBaseAttr(@RequestBody BaseAttrInfo baseAttrInfo){
        return manageService.saveBaseAttr(baseAttrInfo);
    }

}
