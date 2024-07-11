package com.mole.order.controller;


import com.mole.common.entity.order.OmsCompanyAddress;
import com.mole.order.service.OmsCompanyAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址管理Controller
 */
@RestController
@RequestMapping("/CompanyAddress")
@RequiredArgsConstructor
@Tag(name = "OmsCompanyAddressController", description = "收货地址管理")
public class OmsCompanyAddressController {

    private final OmsCompanyAddressService companyAddressService;

    @Operation(summary = "获取所有收货地址")
    @GetMapping
    public List<OmsCompanyAddress> addressFindAll() {
        List<OmsCompanyAddress> list = companyAddressService.addressFindAll();
        return list;
    }
}
