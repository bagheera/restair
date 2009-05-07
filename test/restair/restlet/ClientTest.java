package restair.restlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Client;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.StringRepresentation;

public class ClientTest {
	@Before
	public void setup() throws Exception{
		restair.data.DataSetup.main(null);
	}
	@Test
	public void makeBooking() throws IOException{
        Request request = createRequest("http://127.0.0.1:3000/v1/booking");

        // Action: Create
        request.setMethod(Method.POST);

        BufferedReader reader = new BufferedReader(new FileReader(new File("d:/mycode/restair/src/makeBooking.t")));
        StringBuilder sb = new StringBuilder();
        String s;
        while((s = reader.readLine()) != null) sb.append(s);
        reader.close();
        StringRepresentation srep = new StringRepresentation(sb, MediaType.APPLICATION_XHTML_XML);
        request.setEntity(srep);
        // Prepare HTTP client connector.
        Client client = new Client(Protocol.HTTP);

        // Make the call.
        Response response = client.handle(request);
        Assert.assertEquals(Status.REDIRECTION_SEE_OTHER, response.getStatus());
        Assert.assertTrue(response.getRedirectRef().toString().contains("PXWR"));
		response = client.get(response.getRedirectRef().toString());
		Assert.assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
	private Request createRequest(String requestURL) {
		// Prepare the REST call.
        Request request = new Request();

        // Identify ourselves.
        request.setReferrerRef("http://www.foo.com/");
        // Target resource.
        request.setResourceRef(new Reference(new Reference("http://127.0.0.1:3000/v1"),requestURL));
		return request;
	}
}
