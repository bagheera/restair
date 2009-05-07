package restair.data;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import restair.domain.Booking;
import restair.domain.Flight;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class RealRepository implements Repository {

	private static final int DEPTH = 5;
	private ObjectContainer db;
	private static int bookingRefIndex = 201;

	public RealRepository() throws IOException {
		System.out.println("attempting connection to db...");
        this.db = Db4o.openClient("localhost", 7001, "db4o", "db4o");
        System.out.println("connected.");	
     }
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("closing db...");
		this.db.close();
	}



	public List<Flight> getFlights(final String origin, final String destination, final Date date) {
		return db.query(new Predicate<Flight>(){
			@Override
			public boolean match(Flight candidate) {
				return candidate.matches(origin, destination, date);
			}
		});
	}

	public String nextBookingReference() {
		return "PXWR"+ String.valueOf(bookingRefIndex ++);
	}

	public Flight createFlight(Flight flightTobeCreated) {
		db.set(flightTobeCreated);
		long id = db.ext().getID(flightTobeCreated);
		Flight createdFlight = db.ext().getByID(id);
		db.ext().activate(createdFlight, DEPTH);
		db.commit();
		return createdFlight;
	}

	public Flight getFlight(String flightNumber) {
		return (Flight) db.get(new Flight(null, flightNumber, null, null, null, 0)).get(0);
	}

	public Booking createBooking(Booking newBooking) {
		newBooking.acquireBookingReference();
		db.set(newBooking);
		long id = db.ext().getID(newBooking);
		Booking createdBooking = db.ext().getByID(id);
		db.ext().activate(createdBooking, DEPTH);
		db.commit();
		return createdBooking;
	}

	public void updateFlight(Flight flight) {
		db.set(flight);
		db.commit();
	}
	
	public ObjectContainer db(){
		return db;
	}

	public Booking getBooking(final String bookingRef) {
		ObjectSet<Booking> result = db.query(new Predicate<Booking>(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Booking candidate) {
				return candidate.match(bookingRef);
			}
			
		});
		if(result.hasNext()) return result.next();
		throw new RuntimeException("Booking reference does not exist: "+bookingRef);
	}

	public void updateBooking(Booking booking) {
		db.set(booking);
		db.commit();
	}
}
