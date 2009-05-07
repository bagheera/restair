package restair.restlet;

import static restair.restlet.Util.string2date;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import restair.data.RealRepository;
import restair.domain.Flight;
import restair.domain.Place;
import restair.domain.RestairTest;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class DbTest extends RestairTest{
	RealRepository rep;
	private ObjectContainer db;
	
	@Before
	public void setup() throws IOException, Exception{
        rep = new RealRepository();
        db = rep.db();
		Flight f = new Flight(rep, "101", "MUMBAI", "BANGALORE", string2date("01-02-2008"), 10);
		rep.createFlight(f);
		 f = new Flight(rep, "102", "MUMBAI", "BANGALORE", string2date("11-02-2008"), 10);
		 rep.createFlight(f);
	}
	@After
	public void tearDown(){
        List<Flight> result = rep.getFlights(Place.MUMBAI, Place.BANGALORE, null);
        for(Flight f: result)
        	db.delete(f);
        db.commit();
        db.close();
	}

	@Test
	public void clientServerConnect() throws IOException{
        List<Flight> result = rep.getFlights(Place.MUMBAI, Place.BANGALORE, null);
        for(Flight f: result)
        	System.out.println(f);
	}
	
	@Test
	public void predicateTest(){
		ObjectSet<Flight> result = db.query(new Predicate<Flight>(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Flight candidate) {
				return Place.MUMBAI.equals(candidate.origin());
		}
			
		});
		while(result.hasNext())
        	System.out.println(result.next());
		Assert.assertEquals(2, result.size());
	}
	
}
