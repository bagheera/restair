package restair.data;

import java.io.File;
import java.io.IOException;

import com.db4o.Db4o;
import com.db4o.ObjectServer;
import com.db4o.config.Configuration;

public class DbServer {
	public static void main(String args[]) throws IOException{
        /** Open and keep the db4o object container. */
        Configuration config = Db4o.configure();

        config.updateDepth(2);
        config.optimizeNativeQueries(false);
//        config.markTransient("Flight.db");
//        config.markTransient("Place.db");
//        config.markTransient("Booking.db");
//        config.markTransient("Traveller.db");
        ObjectServer server = Db4o.openServer(config, System.getProperty("user.home")
                + File.separator + "restair.dbo", 7001);

        server.grantAccess("db4o", "db4o");

        System.out.println("Db40 server started...\n press any key to shutdown.");
        System.in.read();
	}
}
