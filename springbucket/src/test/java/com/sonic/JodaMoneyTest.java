package com.sonic;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * JodaMoneyTest
 *
 * @author Sonic
 * @since 2019/4/22
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class JodaMoneyTest {

    @Test
    public void testMoney(){
        BigMoney money = BigMoney.parse("USD 1");

        CurrencyUnit usd = CurrencyUnit.of("USD");
        money = money.plus(Money.of(usd, 4));
        System.out.println(money.getAmount());
        money = money.minusMajor(2);
        System.out.println(money.getAmount());
        BigDecimal conversionRate = BigDecimal.valueOf(6);
        BigMoney moneyCNY = money.convertedTo(CurrencyUnit.of("CNY"), conversionRate);
        System.out.println(moneyCNY.getAmount());
    }


}
