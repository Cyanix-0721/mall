package com.mole.order.controller;

import com.mole.common.entity.order.OmsOrderSetting;
import com.mole.order.service.OmsOrderSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {

    private final OmsOrderSettingService orderSettingService;

    @GetMapping("{id}")
    public ResponseEntity<OmsOrderSetting> getItem(@PathVariable Long id) {
        OmsOrderSetting orderSetting = orderSettingService.getItem(id);
        return ResponseEntity.ok(orderSetting);
    }
}
