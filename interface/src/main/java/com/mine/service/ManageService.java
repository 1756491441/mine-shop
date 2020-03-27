package com.mine.service;

import com.mine.bean.*;

import java.util.List;

public interface ManageService {

    /**
     * 查询一级分类
     * {@link BaseCatalog1}
     * @return
     */
    List<BaseCatalog1> getCatalog1();

    List<BaseCatalog2> getCatalog2(String catalog1Id);

    List<BaseCatalog3> getCatalog3(String catalog2Id);

    List<BaseAttrInfo> getBaseAttrInfo(String catalog3Id);

    List<BaseAttrValue> getBaseAttrValue(String attrInfoId);

    String saveBaseAttr(BaseAttrInfo baseAttrInfo);

    BaseAttrInfo getBaseAttrInfoByPrimaryKey(String attrInfoId);
}
