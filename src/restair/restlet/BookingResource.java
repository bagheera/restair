package restair.restlet;

import java.io.IOException;
import java.util.ArrayList;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.Representation;
import org.restlet.resource.Variant;

import restair.domain.Booking;
import restair.domain.Flight;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BookingResource extends RestairResource{

	public BookingResource(Context context, Request request, Response response) {
		super(context, request, response);
		getVariants().add(new Variant(MediaType.APPLICATION_XHTML_XML));
	}

	@Override
	public boolean allowPost() {
		return true;
	}

	@Override
	public boolean allowDelete() {
		return true;
	}

	@Override
	public void delete() {
		try {
			Booking bookingToBeCancelled = getBooking();
			bookingToBeCancelled.useRepository(repository());
			bookingToBeCancelled.cancel();
			ArrayList list = new ArrayList();
			list.add(bookingToBeCancelled);
			Representation result = packageIntoResult(list);
			result.setMediaType(MediaType.APPLICATION_XHTML_XML);
			this.getResponse().setEntity(result);
			this.getResponse().setStatus(Status.SUCCESS_OK, "Booking cancelled");
		} catch (RuntimeException e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e.getMessage());
		}
	}

	@Override
	public void post(Representation entity) {
		MediaType postedMediaType = entity.getMediaType();
		if(isntXHTML(postedMediaType)){
			respondSayingUnsupported();
			return;
		}
		XStream x = new XStream(new DomDriver());
		Converter converter = new DivSpanConverter(new BookingConverter(x));
		x.registerConverter(converter);
		x.aliasAttribute("clazz", "class");
		try {
			String postBody = entity.getText();
			Booking bookingRequest = (Booking) x.fromXML(postBody);
			Flight requestedFlight = repository().getFlight(bookingRequest.flightNumber());
			requestedFlight.useRepository(repository());
			Booking actualBooking;
			try {
				actualBooking = bookingRequest.book(requestedFlight);
			} catch (RuntimeException e) {
				getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e.getMessage());
				return;
			}
			ArrayList list = new ArrayList();
			list.add(actualBooking);
			Representation result = packageIntoResult(list);
			result.setMediaType(MediaType.APPLICATION_XHTML_XML);
			System.out.println(x.toXML(actualBooking));
			this.getResponse().setEntity(result);
//			this.getResponse().setStatus(Status.SUCCESS_CREATED);
			this.getResponse().redirectSeeOther("v1/booking/"+actualBooking.getBookingReference());
			//TODO done return link to created booking
			//TODO done handle case for all booked i.e. return failure
			//TODO done implement cancel booking via delete
			//TODO reschedule via PUT
			//TODO json variant
			// TODO done CSS on browser
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Bad Entity");
		}
	}

	@Override
	protected SpecificConverter getSpecificConverter(XStream x) {
		return new BookingConverter(x);
	}

	@Override
	public Representation getRepresentation(Variant variant) {
		if(variant ==null || isntXHTML(variant.getMediaType())){
			respondSayingUnsupported();
			return null;
		}
		try {
			Booking requestedBooking = getBooking();
			ArrayList list = new ArrayList(); list.add(requestedBooking);
			Representation bookingRepresentation = packageIntoResult(list);
			getResponse().setStatus(Status.SUCCESS_OK);
			return bookingRepresentation;
		} catch (RuntimeException e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e.getMessage());
			return null;
		}
	}

	private Booking getBooking() {
		String bookingRef = (String) getRequest().getAttributes().get("bookingReference");
		Booking requestedBooking = repository().getBooking(bookingRef);
		return requestedBooking;
	}

}
