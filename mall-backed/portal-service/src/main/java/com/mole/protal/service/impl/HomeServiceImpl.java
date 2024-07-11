package com.mole.protal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.mole.protal.domain.FlashPromotionProduct;
import com.mole.protal.domain.HomeContentResult;
import com.mole.protal.domain.HomeFlashPromotion;
import com.mole.common.entity.portal.*;
import com.mole.protal.mapper.*;
import com.mole.protal.service.HomeService;
import com.mole.protal.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 首页内容管理Service实现类

 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;

    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private CmsSubjectMapper subjectMapper;
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());
        //获取推荐品牌
        result.setBrandList(brandMapper.getRecommendBrandList(0, 6));
        //获取秒杀信息
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        //获取新品推荐
        result.setNewProductList(productMapper.getNewProductList(0,4));
        //获取人气推荐
        result.setHotProductList(productMapper.getHotProductList(0,4));
        //获取推荐专题
        result.setSubjectList(subjectMapper.getRecommendSubjectList(0,4));
        return result;
    }

    @Override
    public List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum) {
        // 设置分页信息
        PageHelper.startPage(pageNum, pageSize);

        // 假设您的PmsProductMapper中有一个自定义方法，该方法直接根据删除状态和发布状态查询商品
        return productMapper.selectByStatus(0, 1);
    }

    @Override
    public List<PmsProductCategory> getProductCateList(Long parentId) {
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status", 1) // 筛选show_status为1的记录
                .eq("parent_id", parentId) // 根据parentId筛选
                .orderByDesc("sort"); // 按sort字段降序排序

        return productCategoryMapper.selectList(queryWrapper); // 使用MyBatis-Plus的selectList方法执行查询
    }

    @Override
    public List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        // 使用MyBatis-Plus的Page对象进行分页设置
        Page<CmsSubject> page = new Page<>(pageNum, pageSize);

        // 创建LambdaQueryWrapper并根据cateId添加筛选条件
        LambdaQueryWrapper<CmsSubject> queryWrapper = new LambdaQueryWrapper<>();
        // 只有当cateId不为null时，才添加categoryId等于cateId的筛选条件
        if (cateId != null) {
            queryWrapper.eq(CmsSubject::getCategoryId, cateId);
        }

        // 执行带有筛选条件的分页查询
        subjectMapper.selectPage(page, queryWrapper);

        // 返回分页数据中的记录列表
        return page.getRecords();
    }


    @Override
    public List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return productMapper.getHotProductList(offset, pageSize);
    }

    @Override
    public List<PmsProduct> newProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return productMapper.getNewProductList(offset, pageSize);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //获取当前秒杀活动
        Date now = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            //获取当前秒杀场次
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                //获取下一个秒杀场次
                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if(nextSession!=null){
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                //获取秒杀商品
                List<FlashPromotionProduct> flashProductList = flashPromotionMapper.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    //获取下一个场次信息
    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        return promotionSessionMapper.getNextFlashPromotionSessionByDate(date);
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        // 假设advertiseMapper有一个方法可以直接接受条件和排序参数
        return advertiseMapper.selectByTypeAndStatusOrderByIdDesc(1, 1);
    }
    //根据时间获取秒杀活动
    private SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        return flashPromotionMapper.getActiveFlashPromotionByDate(currDate);
    }

    //根据时间获取秒杀场次
    private SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        return promotionSessionMapper.findOngoingSession(currTime);
    }
}
