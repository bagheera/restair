package restair.restlet;

import restair.domain.Booking;
import restair.domain.Flight;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DivSpanConverter implements Converter {
	
	private SpecificConverter specific;

	public DivSpanConverter(SpecificConverter specific) {
		this.specific = specific;
	}

	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
//		if("Flight".equalsIgnoreCase(clazz)){
//			Flight f = (Flight)source;
//			f.writeStream(writer);
//		}else if("Booking".equalsIgnoreCase(clazz)){
//			Booking b = (Booking)source;
//			b.writeStream(writer);
//		}
		specific.writeStream(source, writer);
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
//		if("Flight".equalsIgnoreCase(clazz)){
//			reader.moveDown();
//			String attrib = reader.getValue();
//			return new Flight(null, attrib, "aaa", "bbb", new Date(), 10);
//		}else if("Booking".equalsIgnoreCase(clazz)){
//			reader.moveDown();
//		}
		return specific.readStream(reader);
	}

	public boolean canConvert(Class type) {
		return type.equals(Flight.class) || type.equals(Booking.class);
	}

}
