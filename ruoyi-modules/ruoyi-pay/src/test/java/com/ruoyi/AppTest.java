package com.ruoyi;

import com.ruoyi.pay.RuoYiPayApplication;
import com.ruoyi.pay.domain.Order;
import com.ruoyi.pay.service.AliPayService;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiPayApplication.class)
public class AppTest extends TestCase
{
    @Autowired
    private AliPayService aliPayService;
    @Test
    public void testpay()
    {
        Order order = new Order();
        aliPayService.makeOrder(order);
    }
}
