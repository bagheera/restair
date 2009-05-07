package restair.data;

import static restair.restlet.Util.string2date;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import restair.domain.Booking;
import restair.domain.Flight;
import restair.domain.Traveller;

public class DataSetup {
	public static void main(String[] args) throws Exception{
        RealRepository rep = new RealRepository();
        ObjectContainer db = rep.db();
        
        ObjectSet<Flight> oldFlights = db.query(Flight.class);
        while(oldFlights.hasNext()) db.delete(oldFlights.next());
        ObjectSet<Booking> oldBookings = db.query(Booking.class);
        while(oldBookings.hasNext()) db.delete(oldBookings.next());
        ObjectSet<Traveller> oldTravellers = db.query(Traveller.class);
        while(oldTravellers.hasNext()) db.delete(oldTravellers.next());
        db.commit();
		Flight f = new Flight(rep, "101", "MUMBAI", "BANGALORE", string2date("01-01-2008"), 10);
		rep.createFlight(f);
		 f = new Flight(rep, "102", "MUMBAI", "BANGALORE", string2date("02-02-2008"), 10);
		 rep.createFlight(f);
		 f = new Flight(rep, "103", "BANGALORE", "MUMBAI", string2date("11-11-2007"), 10);
		 rep.createFlight(f);
		 f = new Flight(rep, "104", "PUNE", "OOTY", string2date("04-01-2008"), 10);
		 rep.createFlight(f);
		 db.close();
	}
}
