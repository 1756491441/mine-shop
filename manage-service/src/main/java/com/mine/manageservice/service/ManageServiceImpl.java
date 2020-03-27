package com.mine.manageservice.service;

import com.mine.bean.*;
import com.mine.manageservice.mapper.*;
import com.mine.service.ManageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/3/26 14:07
 **/
@Service
@Transactional
public class ManageServiceImpl implements ManageService {
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        return baseCatalog3Mapper.select(baseCatalog3);
    }

    @Override
    public List<BaseAttrInfo> getBaseAttrInfo(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return baseAttrInfoMapper.select(baseAttrInfo);
    }

    @Override
    public List<BaseAttrValue> getBaseAttrValue(String attrInfoId) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrInfoId);
        return baseAttrValueMapper.select(baseAttrValue);
    }

    @Override
    public String saveBaseAttr(BaseAttrInfo baseAttrInfo) {
        if(!StringUtils.isBlank(baseAttrInfo.getId())){
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{
            baseAttrInfo.setId(null);
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        //根据attrId先全部删除，再统一保存
        Example example = new Example(BaseAttrValue.class);
        example.createCriteria()
                .andEqualTo("attrId", baseAttrInfo.getId());
        baseAttrValueMapper.deleteByExample(example);

        List<BaseAttrValue> baseAttrValues = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue baseAttrValue:baseAttrValues) {
            String attrId = baseAttrInfo.getId();
            baseAttrValue.setAttrId(attrId);
            baseAttrValueMapper.insertSelective(baseAttrValue);
        }
        return "success";
    }

    @Override
    public BaseAttrInfo getBaseAttrInfoByPrimaryKey(String attrInfoId) {
        BaseAttrInfo result = baseAttrInfoMapper.selectByPrimaryKey(attrInfoId);
        BaseAttrValue attrValue = new BaseAttrValue();
        attrValue.setAttrId(result.getId());
        List<BaseAttrValue> attrValues = baseAttrValueMapper.select(attrValue);
        result.setAttrValueList(attrValues);
        return result;
    }
}
