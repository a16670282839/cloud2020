package com.atguigu.springcloud.controller;

import com.atguigu.spring.entities.CommonResult;
import com.atguigu.spring.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int resource = paymentService.create(payment);
        log.info("*****插入结果：" + resource);

        if (resource > 0) {
            return new CommonResult(200, "插入成功,serverPort" + serverPort,resource);
        }else{
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功,serverPort" + serverPort, paymentById);
        } else {
            return new CommonResult(444, "没有对应的记录，查询ID：" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }
}
