package restair.domain;

import static restair.restlet.Util.date2string;
import static restair.restlet.Util.span;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import restair.data.Repository;
import restair.restlet.Div;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
public class Flight {
    private static final long serialVersionUID = 10062007L;
	private transient Repository db;
	private final String flightNumber;
	private final String origin;
	private final String destination;
	private final Date flightDate;
	private final int capacity;
	private int seatsTaken = 0;

	public Flight(Repository db) {
		this(db, null, null, null, null, 0);
	}

	public Flight(Repository db, String flightNumber, String origin, String destination, Date flightDate, int capacity) {
		this.db = db;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.flightDate = flightDate;
		this.capacity = capacity;
	}

	public List<Flight> availableFlightsFor(String origin, String destination, Date date) {
		List<Flight> potentialFlights = db.getFlights(origin, destination, date);
		List<Flight> availableFlights = new ArrayList<Flight>();
		for(Flight f : potentialFlights){
			if(f.isFull()) continue;
			availableFlights.add(f);
		}
		return availableFlights;
	}

	private boolean isFull() {
		return capacity == seatsTaken;
	}

	public synchronized Booking book(List<Traveller> travellers) {
		if(canBook(travellers.size())){
			seatsTaken += travellers.size();
			db.updateFlight(this);
		}
		else
			throw new RuntimeException("Seats unavailable");
		Booking newBooking = new Booking(db, this.flightNumber, travellers);
		return db.createBooking(newBooking);
	}

	private boolean canBook(int numberOfSeats) {
		return capacity >= seatsTaken + numberOfSeats;
	}

	public boolean matches(String desiredOrigin, String desiredDestination, Date desiredDate) {
		return this.origin.equals(desiredOrigin) && this.destination.equals(desiredDestination) && (desiredDate == null || isSameDay(desiredDate));
	}
	
	private boolean isSameDay(Date desiredDate) {
		return flightDate.getYear() == desiredDate.getYear() &&
			flightDate.getMonth() == desiredDate.getMonth() &&
			flightDate.getDate() == desiredDate.getDate();
	}

	public String toString(){
		Div d = new Div("flight");
		d.addSpan("flightNumber", flightNumber);
		d.addSpan("flightDate", date2string(flightDate));
		d.addSpan("origin", this.origin);
		d.addSpan("destination", this.destination);
		d.addSpan("capacity", String.valueOf(this.capacity));
		d.addSpan("seatsTaken", String.valueOf(this.seatsTaken));
		return d.toString();
	}

	public Object origin() {
		return this.origin;
	}

	public void writeStream(HierarchicalStreamWriter writer) {
		writer.addAttribute("class", "flight");
		span(writer, "flightNumber", flightNumber);
		span(writer, "flightDate", date2string(flightDate));
		span(writer, "origin", origin);
		span(writer, "destination", destination);
		span(writer, "capacity", String.valueOf(capacity));
		span(writer, "seatsTaken", String.valueOf(seatsTaken));
	}

	public void useRepository(Repository repository) {
		this.db = repository;
	}

	public void setSeatsTaken(int seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

	public void releaseSeats(int numberOfSeats) {
		if(seatsTaken < numberOfSeats) throw new RuntimeException("Cannot release "+numberOfSeats+" when only "+ this.seatsTaken+ " are taken");
		this.seatsTaken -= numberOfSeats;
		db.updateFlight(this);
	}
}
