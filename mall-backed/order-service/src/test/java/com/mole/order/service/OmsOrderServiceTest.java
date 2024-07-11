package com.mole.order.service;


import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.order.dto.OmsOrderQueryParam;
import com.mole.common.entity.order.OmsOrder;
import com.mole.order.mapper.OmsOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class OmsOrderServiceTest {

   @Autowired
   private OmsOrderService omsOrderService;

   @Autowired
   private OmsOrderMapper omsOrderMapper;


   // Test to find all data
   @Test
   public void testFindAll() {
       List<OmsOrder> orders = omsOrderService.findAll();
       assertNotNull(orders); // Optional: 添加断言以确保返回的订单列表不为空
       System.out.println(orders);
   }

   @Test
    public void testFindById() {
        OmsOrder order = omsOrderService.findById(13L);
        assertNotNull(order);
        System.out.println(order);
    }

   @Test
    public void testCreateOrder() {
        OmsOrder order = new OmsOrder();
        order.setMemberId(1L);
        order.setReceiverName("test");
        order.setReceiverPhone("12345678910");
        order.setDeleteStatus(0);
        int result = omsOrderService.createOrder(order);
        assert result > 0;
        System.out.println(result);
        System.out.println(order);
    }

    @Test
    public void testUpdateOrder() {
        OmsOrder order = new OmsOrder();
        order.setId(76L);
        order.setMemberId(1L);
        order.setReceiverName("testUpdate");
        order.setReceiverPhone("0987654321");
        order.setDeleteStatus(0);
        boolean result = omsOrderService.updateOrder(order);
        assertTrue(result);
    }

    @Test
    public void testListOrdersWithParams() {
        // 准备测试数据（这部分通常在Spring Boot的测试配置中通过模拟数据库或使用内存数据库实现）
        // 假设已有数据准备，直接进行查询测试

        // 构造查询参数
        OmsOrderQueryParam queryParam = new OmsOrderQueryParam();
        //queryParam.setOrderSn("testOrderSn"); // 示例订单号
        //queryParam.setStatus(1); // 示例订单状态
        //queryParam.setReceiverKeyword("大梨");

        // 分页参数
        int pageSize = 10;
        int pageNum = 1;

        System.out.println("查询参数: " + queryParam);
        // 调用服务层方法
        IPage<OmsOrder> resultPage = omsOrderService.list(queryParam, pageSize, pageNum);
        System.out.println(resultPage);

        // 验证结果
        assertNotNull(resultPage, "查询结果不应为空");
        assertEquals(pageSize, resultPage.getSize(), "每页大小应匹配");
        assertEquals(pageNum, resultPage.getCurrent(), "当前页码应匹配");

        //打印
        System.out.println("当前页码: " + resultPage.getCurrent());
        System.out.println("每页大小: " + resultPage.getSize());
        System.out.println("总记录数: " + resultPage.getTotal());
        System.out.println("总页数: " + resultPage.getPages());
    }


}
