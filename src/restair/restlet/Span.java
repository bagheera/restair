package restair.restlet;

public class Span {

	private final String spanClass;
	private final String spanText;

	public Span(String spanClass, String spanText) {
		this.spanClass = spanClass;
		this.spanText = spanText;
	}

	@Override
	public String toString() {
		return "<span class=\"" + spanClass + "\">" + spanText + "</span>";
	}

}
