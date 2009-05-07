package restair.restlet;

import java.io.IOException;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Router;
import org.restlet.data.Protocol;

import restair.data.RealRepository;
import restair.data.Repository;


public class Application extends org.restlet.Application {
	public static final String KEY = "restair";
	private Repository repository;
	
    public static void main(String... args) throws Exception {
        // Create a component with an HTTP server connector
        Component comp = new Component();
        comp.getServers().add(Protocol.HTTP, 3000);
        comp.getDefaultHost().attach("/v1", new Application(comp.getContext()));
        comp.start();
    }

    public Application(Context context) throws IOException {
    	super(context);
    	this.repository = new RealRepository();
//    	context.
    }

    @Override
    public Restlet createRoot() {
        Router router = new Router(getContext());

        router.attach("/flights?journeyDate={journeyDate}&from={origin}&to={destination}", FlightResource.class);
        router.attach("/flights?from={origin}&to={destination}", FlightResource.class);
        router.attach("/booking", BookingResource.class);
        router.attach("/booking/{bookingReference}", BookingResource.class);
        
//TODO figure out template variables
//        uriRoute.getTemplate().getVariables().put("URI",
//                new Variable(Variable.TYPE_URI_ALL));

        return router;
    }
    
    public Repository repository(){
    	return this.repository;
    }
}
