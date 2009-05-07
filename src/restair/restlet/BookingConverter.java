package restair.restlet;

import java.util.ArrayList;
import java.util.List;

import restair.domain.Booking;
import restair.domain.Traveller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class BookingConverter extends SpecificConverterBase{

	private String flightNumber;
	private String firstName;
	private String gender;
	private String lastName;
	private List<Traveller> travellers;
	private String bookingRef;
	private String bookingStatus;


	public BookingConverter(XStream x) {
		x.alias("div", Booking.class);
	}


	public Object readStream(HierarchicalStreamReader reader) {//deserialize

		while(reader.hasMoreChildren()){
			reader.moveDown();
			if(currentNodeIsASpan(reader)) updateField(name(reader), fieldValue(reader));
			else if(currentNodeIsADiv(reader)) handleChild(name(reader), reader);
			reader.moveUp();
		}
		Booking b = new Booking(null, flightNumber, travellers);
		b.setBookingReference(bookingRef);
		b.setBookingStatus(bookingStatus);
		return b;
	}


	private void handleChild(String childType, HierarchicalStreamReader reader) {
		if("travellers".equalsIgnoreCase(childType)){
			travellers = new ArrayList<Traveller>();
			while(reader.hasMoreChildren()){
				reader.moveDown();
				if(currentNodeIsADiv(reader)) handleChild(name(reader), reader);
				reader.moveUp();
			}
		}
		else if(("traveller").equalsIgnoreCase(childType)){
			while(reader.hasMoreChildren()){
				reader.moveDown();
				if(currentNodeIsASpan(reader)) updateField(name(reader), fieldValue(reader));
				reader.moveUp();
			}
			travellers.add(new Traveller(firstName, lastName, gender));
		}
	}


	private void updateField(String fieldName, String fieldValue) {
		if("flightNumber".equalsIgnoreCase(fieldName)) flightNumber = fieldValue;
		if("bookingReference".equalsIgnoreCase(fieldName)) bookingRef = fieldValue;
		if("bookingStatus".equalsIgnoreCase(fieldName)) bookingStatus = fieldValue;//should not come from outside
		else if("firstName".equalsIgnoreCase(fieldName)) firstName = fieldValue;
		else if("lastName".equalsIgnoreCase(fieldName)) lastName = fieldValue;
		else if("gender".equalsIgnoreCase(fieldName)) gender = fieldValue;
	}

	public void writeStream(Object source, HierarchicalStreamWriter writer) {//serialize
		Booking b = (Booking)source;
		b.writeStream(writer);
	}
}
