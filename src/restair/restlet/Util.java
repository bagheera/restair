package restair.restlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class Util {
	public static Date string2date(String strDate) throws ParseException {
		return sdf.parse(strDate);
	}
	
	public static String date2string(Date date){
		return sdf.format(date);
	}
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


	public static void span(HierarchicalStreamWriter writer, String spanClass, String spanText) {
		writer.startNode("span");
		writer.addAttribute("class", spanClass);
		if(spanText != null) writer.setValue(spanText);
		writer.endNode();		
	}
	
	public static String bookingUrl(String bookingReference){
		return "http://localhost:3000/v1/booking/" + bookingReference;
	}

}
