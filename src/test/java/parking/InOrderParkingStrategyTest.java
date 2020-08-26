package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        Car mockCar = mock(Car.class);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        when(mockParkingLot.isFull()).thenReturn(false);
        when(mockParkingLot.getParkedCars()).thenReturn(new ArrayList<Car>());
        when(mockParkingLot.getName()).thenReturn("SKY ParkingLot");
        when(mockCar.getName()).thenReturn("A888888");
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(mockParkingLot),mockCar);
        Assert.assertEquals("SKY ParkingLot",receipt.getParkingLotName());
        Assert.assertEquals("A888888",receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car mockCar = mock(Car.class);
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        when(mockParkingLot.isFull()).thenReturn(true);
        when(mockCar.getName()).thenReturn("A888888");
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(mockParkingLot),mockCar);
        Assert.assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
        Assert.assertEquals("A888888",receipt.getCarName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        Car spyCar = spy(new Car("A88888"));

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(null,spyCar);
        Assert.assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
        verify(spyCar,times(1)).getName();

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        Car spyCar = spy(new Car("A88888"));
        ParkingLot spyParkingLot = spy(new ParkingLot("HardyParkinglot",1));
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(spyParkingLot),spyCar);
        Assert.assertEquals("HardyParkinglot",receipt.getParkingLotName());
        Assert.assertEquals("A88888",receipt.getCarName());
        verify(spyParkingLot,times(1)).getName();
        verify(spyCar,times(1)).getName();
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        Car spyCar = spy(new Car("A88888"));
        ParkingLot spyParkingLot = spy(new ParkingLot("HardyParkinglot",0));
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(spyParkingLot),spyCar);
        Assert.assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
        verify(spyParkingLot,times(0)).getName();
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
