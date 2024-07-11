package com.mole.marketing.mapper;
import com.mole.common.entity.marketing.SmsCoupon;
import com.mole.marketing.service.SmsCouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SmsCoupontest {
    @Autowired
    private SmsCouponMapper smsCouponMapper;

    @Autowired
    private SmsCouponService smsCouponService;

    // Test to find all data
    @Test
    public void testFindAll() {
        List<SmsCoupon> smsCouponList = smsCouponMapper.selectList(null);
        smsCouponList.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        // 创建一个 SmsCoupon 实例并设置其属性
        SmsCoupon newCoupon = new SmsCoupon();
        newCoupon.setId(50L);
        newCoupon.setType(1);
        newCoupon.setName("Coupon1");
        newCoupon.setPlatform(1);
        newCoupon.setCount(100);
        newCoupon.setAmount(new BigDecimal("10.00"));
        newCoupon.setPerLimit(1);
        newCoupon.setMinPoint(new BigDecimal("0.00"));
        newCoupon.setStartTime(new Date());
        newCoupon.setEndTime(new Date(System.currentTimeMillis() + 86400000)); // 1 天后
        newCoupon.setUseType(0);
        newCoupon.setNote("Test Note");
        newCoupon.setPublishCount(100);
        newCoupon.setUseCount(0);
        newCoupon.setReceiveCount(0);
        newCoupon.setEnableTime(new Date());
        newCoupon.setCode("TESTCODE");
        newCoupon.setMemberLevel(0);
        System.out.println("New Coupon Details: " + newCoupon.toString());


        //模拟 mapper insert 方法返回值
        //when(smsCouponMapper.insert(any(SmsCoupon.class))).thenReturn(1);

        // 调用服务方法并断言结果
        //boolean result = smsCouponService.save(newCoupon);
        // assertTrue(result);
    }

    @Test
    public void testFindByType() {
        // 假设数据库中已经有一些类型为0的优惠券，如果没有，可以先调用testSave方法保存一个
        Integer type = 0;
        List<SmsCoupon> coupons = smsCouponService.findByType(type);
        Assert.notEmpty(coupons, "The coupon list for type " + type + " should not be empty");
        coupons.forEach(coupon -> {
            System.out.println(coupon);
            Assert.isTrue(coupon.getType().equals(type), "The coupon type should be " + type);
        });
    }
    // 假设 SmsCouponMapper 已经有 findByName 方法
    @Test
    public void testFindByNamePhoneCoupon() {
        // 定义要查询的优惠券名称
        String expectedName = "手机分类专用券";

        // 调用 findByName 方法查询指定名称的优惠券
        List<SmsCoupon> coupons = smsCouponService.findByName(expectedName);

        // 断言确保查询结果不为空，即找到了至少一张名称匹配的优惠券
        Assert.notEmpty(coupons, "No coupons found with the name '" + expectedName + "'");

        // 进一步断言，验证查询结果中的每一张优惠券的名称都与预期相符
        coupons.forEach(coupon -> {
            System.out.println("Found Coupon: " + coupon);
            Assert.isTrue(expectedName.equals(coupon.getName()),
                    "The coupon's name '" + coupon.getName() + "' does not match the expected name '" + expectedName + "'");
        });
    }
    @Test
    public void testFindByPlatform() {
        // 定义要查询的平台类型
        Integer expectedPlatform = 0;

        // 调用 findByPlatform 方法查询指定平台的优惠券
        List<SmsCoupon> coupons = smsCouponService.findByPlatform(expectedPlatform);

        // 断言确保查询结果不为空，即找到了至少一张平台匹配的优惠券
        Assert.notEmpty(coupons, "No coupons found for platform '" + expectedPlatform + "'");

        // 进一步断言，验证查询结果中的每一张优惠券的平台都与预期相符
        coupons.forEach(coupon -> {
            System.out.println("Found Coupon: " + coupon);
            Assert.isTrue(expectedPlatform.equals(coupon.getPlatform()),
                    "The coupon's platform '" + coupon.getPlatform() + "' does not match the expected platform '" + expectedPlatform + "'");
        });
    }
    @Test
    public void testRemoveById() {
        // Step 1: Save a test coupon if one does not already exist for testing purposes
        SmsCoupon testCoupon = new SmsCoupon();
        testCoupon.setName("Test Coupon for Removal");
        // ... set other necessary fields ...
        smsCouponMapper.insert(testCoupon);
        Long testCouponId = testCoupon.getId();

        // Step 2: Verify the coupon exists before attempting removal
        SmsCoupon couponBeforeRemoval = smsCouponMapper.selectById(testCouponId);
        assertNotNull(couponBeforeRemoval, "The test coupon should exist before removal attempt.");

        // Step 3: Attempt to remove the coupon by its ID
        boolean isRemoved = smsCouponService.removeById(testCouponId);

        // Step 4: Verify the removal was successful
        assertTrue(isRemoved, "The removal operation should return true if successful.");

        // Step 5: Optionally, verify the coupon no longer exists in the database
        SmsCoupon couponAfterRemoval = smsCouponMapper.selectById(testCouponId);
        assertNull(couponAfterRemoval, "The coupon should not exist after successful removal.");
    }
}
