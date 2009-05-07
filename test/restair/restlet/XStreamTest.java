package restair.restlet;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import restair.data.TestRepository;
import restair.domain.Booking;
import restair.domain.Flight;
import restair.domain.RestairTest;
import restair.domain.Traveller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XStreamTest extends RestairTest{
	@Test
	public void flightStream(){
		Flight f = new Flight(null, "888", "aaa", "bbb", new Date(), 10);
		XStream x = new XStream(new DomDriver());
		Converter converter = new DivSpanConverter(new FlightConverter(x));
		x.registerConverter(converter);
		String str = x.toXML(f);
		System.out.println(str);
		
		x.aliasAttribute("clazz", "class");
		Flight f2 = (Flight) x.fromXML(str);
		System.out.println("got it...");
		x.aliasAttribute("class", "class");
		String str2 = x.toXML(f2);
		System.out.println(str2);
//		String expected = str.replaceAll(">\\s*<", "><");
		Assert.assertEquals("Strings are same", str, str2);
	}
	
	@Test
	public void bookingStream(){
		List<Traveller> people = travellers(3);
		Booking b = new Booking(new TestRepository(), "102", people);
		b.acquireBookingReference();
		XStream x = new XStream(new DomDriver());
		Converter converter = new DivSpanConverter(new BookingConverter(x));
		x.registerConverter(converter);
		String str = x.toXML(b);
		System.out.println(str);
		
		x.aliasAttribute("clazz", "class");
		Booking b2 = (Booking) x.fromXML(str);
		System.out.println("got it...");
		x.aliasAttribute("class", "class");
		String str2 = x.toXML(b2);
		System.out.println(str2);
		Assert.assertEquals("Strings are same", str, str2);
	}

}
