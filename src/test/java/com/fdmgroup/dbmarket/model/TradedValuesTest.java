package com.fdmgroup.dbmarket.model;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TradedValuesTest {

	TradedValues tradedValues = Mockito.mock(TradedValues.class);

	@Test
    public void testGettersReturnCorrectResults() {
        when(tradedValues.getCompanyTicker()).thenReturn("ABCD");
        when(tradedValues.getOpenValue()).thenReturn(10.5);
        when(tradedValues.getCloseValue()).thenReturn(12.2);
        when(tradedValues.getHighestValue()).thenReturn(14.8);
        when(tradedValues.getLowestValue()).thenReturn(9.7);
        when(tradedValues.getTradedVolume()).thenReturn(BigDecimal.valueOf(1000000));
        when(tradedValues.getDate()).thenReturn(LocalDate.now());

        Assert.assertEquals("ABCD", tradedValues.getCompanyTicker());
        Assert.assertEquals(10.5, tradedValues.getOpenValue(), 0.001);
        Assert.assertEquals(12.2, tradedValues.getCloseValue(), 0.001);
        Assert.assertEquals(14.8, tradedValues.getHighestValue(), 0.001);
        Assert.assertEquals(9.7, tradedValues.getLowestValue(), 0.001);
        Assert.assertEquals(BigDecimal.valueOf(1000000), tradedValues.getTradedVolume());
        Assert.assertEquals(LocalDate.now(), tradedValues.getDate());
    }

}