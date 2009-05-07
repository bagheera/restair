package restair.restlet;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.DomRepresentation;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import restair.data.Repository;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public abstract class RestairResource  extends Resource {

	public RestairResource(Context context, Request request, Response response) {
		super(context, request, response);
	}

	public Application getApplication() {
	    return (Application) getContext().getAttributes().get("org.restlet.application");
	}

	public Repository repository() {
		return getApplication().repository();
	}

	protected Representation packageIntoResult(List results) {
		Representation result = null;
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">";
		StringBuilder sb = new StringBuilder(header);
		sb.append("<body>");
		XStream x = new XStream(new DomDriver());
		Converter converter = new DivSpanConverter(getSpecificConverter(x));
		x.registerConverter(converter);
		for(Object obj : results){
			sb.append(x.toXML(obj));
		}
		sb.append("</body></html>");
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			builder.setEntityResolver(new EntityResolver(){
	
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					return new InputSource(new StringReader(" "));						
				}
			});
			Document doc = builder.parse(new InputSource(new StringReader(sb.toString())));
			result = new DomRepresentation(MediaType.APPLICATION_XHTML_XML, doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	protected abstract SpecificConverter getSpecificConverter(XStream x);

	protected void respondSayingUnsupported() {
		getResponse().setStatus(Status.CLIENT_ERROR_UNSUPPORTED_MEDIA_TYPE);
	}

	protected boolean isntXHTML(MediaType postedMediaType) {
		return ! postedMediaType.equals(MediaType.APPLICATION_XHTML_XML, true);
	}

}
