package restair.restlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.Representation;
import org.restlet.resource.Variant;

import restair.domain.Flight;

import com.thoughtworks.xstream.XStream;

	public class FlightResource extends RestairResource {
	    private Date journeyDate;
		private String origin;
		private String destination;
	    
		public FlightResource(Context context, Request request, Response response) {
	        super(context, request, response);

	        String strDate = (String) request.getAttributes().get("journeyDate");
			if (strDate != null) {
				try {
					this.journeyDate = new SimpleDateFormat("dd-MM-yyyy")
							.parse(strDate);
				} catch (ParseException e) {
					throw new IllegalArgumentException("Invalid date "
							+ strDate);
				}
			}			
			String strOrigin = (String) request.getAttributes().get("origin");
	        try {
				this.origin = strOrigin;
			} catch (RuntimeException e) {
				throw new IllegalArgumentException("Invalid origin "+ strOrigin);
			}
	        
			String strDestination = (String) request.getAttributes().get("destination");
			try {
				this.destination = strDestination;
			} catch (RuntimeException e) {
				throw new IllegalArgumentException("Invalid destination "+strDestination);
			}
			//TODO figure out what this line means
			getVariants().add(new Variant(MediaType.APPLICATION_XHTML_XML));
	    }

	    @Override
	    public Representation getRepresentation(Variant variant) {
	        Representation result = null;
			if(variant == null || isntXHTML(variant.getMediaType())){
				respondSayingUnsupported();
				return null;
			}
	        
        	List<Flight> flights = new Flight(repository()).availableFlightsFor(origin, destination, journeyDate);
            result = packageIntoResult(flights);
            getResponse().setStatus(Status.SUCCESS_OK);
	        return result;
	    }

		@Override
		protected SpecificConverter getSpecificConverter(XStream x) {
			return new FlightConverter(x); 
		}

}
