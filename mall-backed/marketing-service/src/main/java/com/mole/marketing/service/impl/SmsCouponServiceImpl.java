package com.mole.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import com.mole.common.entity.marketing.SmsCoupon;
import com.mole.marketing.mapper.SmsCouponMapper;
import com.mole.marketing.service.SmsCouponService;

@Service
@RequiredArgsConstructor
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements SmsCouponService {

    private final SmsCouponMapper smsCouponMapper;  // 假设您有一个MyBatis的Mapper接口

    @Override
    public List<SmsCoupon> findAll() {
        return smsCouponMapper.selectList(null);
    }

    @Override
    public boolean save(SmsCoupon smsCoupon) {
        return smsCouponMapper.insert(smsCoupon) > 0;
    }

    @Override
    public boolean updateById(SmsCoupon smsCoupon) {
        return smsCouponMapper.updateById(smsCoupon) > 0;
    }

    @Override
    public boolean removeById(Long id) {
        return smsCouponMapper.deleteById(id) > 0;
    }

    @Override
    public SmsCoupon getById(Long id) {
        return smsCouponMapper.selectById(id);
    }

    @Override
    public List<SmsCoupon> findByType(Integer type) {
        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return smsCouponMapper.selectList(queryWrapper);
    }

    @Override
    public List<SmsCoupon> findByName(String name) {
        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name); // 使用 like 方法进行模糊查询
        return smsCouponMapper.selectList(queryWrapper);
    }

    @Override
    public List<SmsCoupon> findByPlatform(Integer platform) {
        QueryWrapper<SmsCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("platform", platform);
        return smsCouponMapper.selectList(queryWrapper);
    }
    /**
     * 分页获取优惠券列表
     *
     * @param name      优惠券名称
     * @param type      优惠券类型
     * @param pageSize  每页大小
     * @param pageNum   当前页码
     * @return 分页后的优惠券列表
     */

    @Override
    public IPage<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
       //创建分页对象
        Page<SmsCoupon> page = new Page<>(pageNum, pageSize);

         //构建查询条件
       LambdaQueryWrapper<SmsCoupon> queryWrapper = new LambdaQueryWrapper<>();

        //添加查询条件...

        //执行分页查询
       baseMapper.selectPage(page, queryWrapper);

        //确保此处无需手动设置total，MyBatis Plus 应该会自动填充total值
         //如果total始终为0，考虑是否有数据权限插件或其他拦截器影响了查询计数

        return page;
    }

}