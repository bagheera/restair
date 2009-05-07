package restair.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import restair.domain.Booking;
import restair.domain.Flight;
import restair.domain.Place;

public class TestRepository implements Repository {

	private static final String RA_123 = "RA-123";
	private static List<Flight> flights = null;
	private static int bookingRefIndex = 101;
	private Flight flight124;
	private Flight flight123;
	public List<Flight> getFlights(String origin, String destination, Date date) {
		List<Flight> flights = getTestFlights();
		return flights;
	}

	private List<Flight> getTestFlights() {
		if (flights != null) return flights;
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		    flights = new ArrayList<Flight>();
			flight123 = new Flight(this, RA_123,Place.BANGALORE, Place.MUMBAI, sdf.parse("01/01/2008 07:30"), 2);
			flights.add(flight123);
			 flight124 = new Flight(this, "RA-124",Place.BANGALORE, Place.MUMBAI, sdf.parse("01/01/2008 19:00"), 2);
			flights.add(flight124);
			
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return flights;
	}

	public String nextBookingReference() {
		return "PXWR"+ String.valueOf(bookingRefIndex++);
	}

	public Flight createFlight(Flight flightTobeCreated) {
		return flightTobeCreated;
	}

	public Flight getFlight(String flightNumber) {
		return RA_123.equals(flightNumber) ? flight123 : flight124;
	}

	public Booking createBooking(Booking newBooking) {
		return newBooking;
	}

	public void updateFlight(Flight flight) {
	}

	public Booking getBooking(String bookingRef) {
		return null;
	}

	public void updateBooking(Booking booking) {
	}

}
