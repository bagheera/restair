package restair.restlet;

import java.util.ArrayList;
import java.util.List;

public class Div {

	private final String divClass;
	private List<Span> mySpans;
	private StringBuilder innerString;

	public Div(String divClass) {
		this.divClass = divClass;
		mySpans = new ArrayList<Span>();
		innerString = new StringBuilder();
	}

	public Span addSpan(String spanClass, String spanText) {
		Span s = new Span(spanClass, spanText);
		mySpans.add(s);
		return s;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"").append(divClass).append("\">");
		for(Span s : mySpans){
			sb.append(s.toString());
		}
		sb.append(this.innerString);
		sb.append("</div>");
		return sb.toString();
	}

	public void include(String includeString) {
		innerString.append(includeString);
	}

}
