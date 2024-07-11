package com.mole.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.entity.product.CmsPrefrenceArea;
import com.mole.product.mapper.CmsPrefrenceAreaMapper;
import com.mole.product.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选管理Service实现类
 */
@Service
public class CmsPrefrenceAreaServiceImpl extends ServiceImpl<CmsPrefrenceAreaMapper, CmsPrefrenceArea> implements CmsPrefrenceAreaService {

    @Autowired
    private CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return cmsPrefrenceAreaMapper.selectList(null);
    }
}
