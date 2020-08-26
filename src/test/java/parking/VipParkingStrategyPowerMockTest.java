package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ParkingLot.class, Calendar.class})
public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice() {

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        mockStatic(ParkingLot.class, Calendar.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SUNDAY);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        Integer price = vipParkingStrategy.calculateHourlyPrice();
        assertEquals(Integer.valueOf(50), price);
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice() {

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        mockStatic(ParkingLot.class, Calendar.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.MONDAY);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        Integer price = vipParkingStrategy.calculateHourlyPrice();
        assertEquals(Integer.valueOf(40), price);

    }
}
