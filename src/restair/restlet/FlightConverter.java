package restair.restlet;

import java.text.ParseException;

import restair.domain.Flight;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class FlightConverter extends SpecificConverterBase{

	private String flightNumber;
	private String flightDate;
	private String origin;
	private String destination;
	private int capacity;
	private int seatsTaken;

	public FlightConverter(XStream x) {
		super();
		x.alias("div", Flight.class);
	}

	public Object readStream(HierarchicalStreamReader reader) {
		while(reader.hasMoreChildren()){
			reader.moveDown();
			if(currentNodeIsASpan(reader)) updateField(name(reader), fieldValue(reader));
			reader.moveUp();
		}
		try {
			Flight deserializedFlight = new Flight(null, flightNumber, origin, destination, Util.string2date(flightDate), capacity);
			deserializedFlight.setSeatsTaken(seatsTaken);
			return deserializedFlight;
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not convert to Flight " + e.getMessage());
		}
	}

	private void updateField(String fieldName, String fieldValue) {
		if("flightNumber".equalsIgnoreCase(fieldName)) flightNumber = fieldValue;
		if("flightDate".equalsIgnoreCase(fieldName)) flightDate = fieldValue;
		if("origin".equalsIgnoreCase(fieldName)) origin = fieldValue;
		if("destination".equalsIgnoreCase(fieldName)) destination = fieldValue;
		if("capacity".equalsIgnoreCase(fieldName)) capacity = Integer.parseInt(fieldValue);
		if("seatsTaken".equalsIgnoreCase(fieldName)) seatsTaken = Integer.parseInt(fieldValue);
	}

	public void writeStream(Object source, HierarchicalStreamWriter writer) {
		Flight flight = (Flight)source;
		flight.writeStream(writer);
	}

}
