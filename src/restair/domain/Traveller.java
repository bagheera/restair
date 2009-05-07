package restair.domain;

import static restair.restlet.Util.span;
import restair.restlet.Div;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class Traveller {

	private static final long serialVersionUID = 10062007L;
	private final String firstName;
	private final String lastName;
	private final String gender;

	public Traveller(String firstName, String lastName, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	@Override
	public String toString() {
		Div d = new Div("traveller");
		d.addSpan("firstName", firstName);
		d.addSpan("lastName", lastName);
		d.addSpan("gender", gender);
		return d.toString();
	}

	public static final String FEMALE = "F";

	public void writeStream(HierarchicalStreamWriter writer) {
		span(writer, "firstName", firstName);
		span(writer, "lastName", lastName);
		span(writer, "gender", gender);
	}

}
