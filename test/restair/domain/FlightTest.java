package restair.domain;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import restair.data.Repository;
import restair.data.TestRepository;

public class FlightTest extends RestairTest {
	@Test
	public void getFlightInfo() {
		List<Flight> availableFlights = availableFlights();
		assertEquals(2, availableFlights.size());
	}

	private List<Flight> availableFlights(){
		Repository db = new TestRepository();
		Flight f = new Flight(db);
		List<Flight> availableFlights;
		try {
			availableFlights = f.availableFlightsFor(Place.BANGALORE, Place.MUMBAI, new SimpleDateFormat("dd/mm/yyyy").parse("01/01/2008"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return availableFlights;
	}
	
	@Test
	public void bookMoreThanAvailable(){
		List<Flight> availableFlights = availableFlights();
		Flight firstFlight = availableFlights.get(0);
		try{
			firstFlight.book(travellers(3));
		}catch(RuntimeException success){}
	}

	@Test
	public void bookAfterCancel(){
		List<Flight> availableFlights = availableFlights();
		Flight firstFlight = availableFlights.get(0);
		Booking bookingToBeCancelledLater = firstFlight.book(travellers(2));
		try{
			firstFlight.book(travellers(1));
		}catch(RuntimeException success){}
		bookingToBeCancelledLater.cancel();
		firstFlight.book(travellers(1));//now this should go thru
	}
	
	@Test
	public void bookSuccess(){
		List<Flight> availableFlights = availableFlights();
		Flight firstFlight = availableFlights.get(0);
		Booking booking = firstFlight.book(travellers(1));
		assertEquals(Booking.CONFIRMED, booking.status());
	}
}
