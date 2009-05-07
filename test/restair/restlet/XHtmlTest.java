package restair.restlet;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Assert;

import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.resource.DomRepresentation;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class XHtmlTest {
	@Test
	public void testDomRep() throws Exception, IOException{
		String somexml = "<div class=\"flight\"><span class=\"date\">04-05-2007</span><span class=\"from\">Mumbai</span><span class=\"to\">Bangalore</span></div>";
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(somexml)));
		new DomRepresentation(MediaType.APPLICATION_XHTML_XML, doc).write(System.out);
	}
	
	@Test
	public void divSpanTest(){
		Div d = new Div("Flight");
		d.addSpan("flightNumber", "101");
		d.addSpan("flightDate", "04-05-2007");
		Assert.assertEquals("<div class=\"Flight\"><span class=\"flightNumber\">101</span><span class=\"flightDate\">04-05-2007</span></div>", 
				d.toString());
	}
	
}
