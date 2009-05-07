package restair.data;

import java.util.Date;
import java.util.List;

import restair.domain.Booking;
import restair.domain.Flight;

public interface Repository {

	List<Flight> getFlights(String origin, String destination, Date date);

	String nextBookingReference();
	
	Flight createFlight(Flight flightTobeCreated);

	Flight getFlight(String flightNumber);

	Booking createBooking(Booking newBooking);

	void updateFlight(Flight flight);

	Booking getBooking(String bookingRef);

	void updateBooking(Booking booking);

}
