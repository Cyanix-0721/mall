package com.mole.order.service;


import com.mole.common.entity.order.OmsCompanyAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OmsCompanyAddressServiceTest {

    @Autowired
    private OmsCompanyAddressService omsCompanyAddressService;

    @Test
    public void testFindAll() {
        List<OmsCompanyAddress> omsCompanyAddresses = omsCompanyAddressService.addressFindAll();
        assertNotNull(omsCompanyAddresses);
        System.out.println(omsCompanyAddresses);
    }
}
