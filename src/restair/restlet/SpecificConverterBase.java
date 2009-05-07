package restair.restlet;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;

public abstract class SpecificConverterBase implements SpecificConverter{

	protected boolean currentNodeIsADiv(HierarchicalStreamReader reader) {
		return "div".equalsIgnoreCase(reader.getNodeName());
	}

	protected String name(HierarchicalStreamReader reader) {
		return reader.getAttribute("class");
	}

	protected String fieldValue(HierarchicalStreamReader reader) {
		return reader.getValue();
	}

	protected boolean currentNodeIsASpan(HierarchicalStreamReader reader) {
		return "span".equalsIgnoreCase(reader.getNodeName());
	}
}