package masoncsc.properties;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class TimestampedProperties extends Properties {
	private static final long serialVersionUID = 1L;
	public long time;
	
	static public TimestampedProperties parse(String s) throws IOException {
		TimestampedProperties props = new TimestampedProperties();
		props.load(new StringReader(s));
		if (props.size() == 0)
			return null;
		props.time = Long.parseLong(props.getProperty("time"));
		props.remove("time"); // remove it from the properties since it's held in a local variable
		
		return props;
	}
}