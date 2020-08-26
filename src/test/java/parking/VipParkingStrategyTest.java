package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    CarDao carDao;

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;


    @Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        ParkingLot spyFullParkingLot = spy(new ParkingLot("HardyParkinglot", 0));
        Car spyCar = spy(new Car("A88888"));
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(spyFullParkingLot), spyCar);
        verify(spyCar, times(1)).getName();
        verify(spyFullParkingLot, times(1)).getName();

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        ParkingLot spyFullParkingLot = spy(new ParkingLot("HardyParkinglot", 0));
        Car spyCar = spy(new Car("88888"));
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(any());
        Receipt receipt = vipParkingStrategy.park(Collections.singletonList(spyFullParkingLot), spyCar);
        assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
        verify(spyFullParkingLot, times(0)).getName();
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(true);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar("A8888"));
        assertTrue(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(true);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar("8888"));
        assertFalse(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(false);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar("A8888"));
        assertFalse(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(false);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar("8888"));
        assertFalse(isVip);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
