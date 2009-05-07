package restair.domain;

import java.util.ArrayList;
import java.util.List;

public class RestairTest {

	protected List<Traveller> travellers(int count) {
		List<Traveller> travellers = new ArrayList<Traveller>();
		for(int i = 0; i<count; i++){
			travellers.add(new Traveller("firstName"+i, "lastName"+i, Traveller.FEMALE));
		}
		return travellers;
	}
	
}
