package restair.restlet;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public interface SpecificConverter {

	void writeStream(Object source, HierarchicalStreamWriter writer);

	Object readStream(HierarchicalStreamReader reader);

}
