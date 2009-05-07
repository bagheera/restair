package restair.domain;

import static restair.restlet.Util.*;

import java.util.List;

import restair.data.Repository;
import restair.restlet.Div;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class Booking {

	private static final long serialVersionUID = 10072007L;
	public static final String CONFIRMED = "CONFD";
	public static final String CANCELLED = "CNCLD";;
	private final List<Traveller> travellers;
	private transient Repository db;
	private String bookingReference;
	private String status;
	private String flightNumber;

	public Booking(Repository db, String flightNumber, List<Traveller> travellers) {
		this.db = db;
		this.flightNumber = flightNumber;
		this.travellers = travellers;
		this.status = CONFIRMED;
	}

	public String status() {
		return this.status;
	}
	
	public void cancel(){
		if(! CONFIRMED.equals(this.status)) throw new RuntimeException("Only confirmed bookings may be cancelled");
		Flight f = db.getFlight(flightNumber);
		f.useRepository(db);
		f.releaseSeats(travellers.size());
		this.status = CANCELLED;
		db.updateBooking(this);
	}

	@Override
	public String toString() {
		Div d = new Div("flightBooking");
		d.addSpan("bookingReference", this.bookingReference);
		d.include("<div class=\"travellers\">");
		for(Traveller t : travellers)
			d.include(t.toString());
		d.include("</div>");
		return d.toString();
	}

	public Booking book(Flight requestedFlight) {
		return requestedFlight.book(travellers);
	}

	public void writeStream(HierarchicalStreamWriter writer) {
		writer.addAttribute("class", "flightBooking");
		span(writer, "url", bookingUrl(this.bookingReference));
		span(writer, "bookingReference", this.bookingReference);
		span(writer, "bookingStatus", this.status);
		span(writer, "flightNumber", this.flightNumber);
		writer.startNode("div");
		writer.addAttribute("class", "travellers");
		for(Traveller t : travellers){
			writer.startNode("div");
			writer.addAttribute("class", "traveller");
			t.writeStream(writer);
			writer.endNode();
		}
		writer.endNode();
		
		
	}

	public String flightNumber() {
		return this.flightNumber;
	}

	public void acquireBookingReference() {
		this.bookingReference = db.nextBookingReference();
	}

	public void setBookingReference(String bookingRef) {
		this.bookingReference = bookingRef;
	}

	public void setBookingStatus(String bookingStatus) {
		this.status = bookingStatus;
	}

	public boolean match(String bookingRef) {
		return this.bookingReference.equals(bookingRef);
	}

	public void useRepository(Repository repository) {
		this.db = repository;
	}

	public String getBookingReference() {
		return this.bookingReference;
	}

}
