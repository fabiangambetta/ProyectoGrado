package XES;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XLog;

public class XESParser {

	public static void main(String[] args) {
		loadXML();

	}

	public static void loadXML() {
		try 
		{
			String pathtext = "c:/running-example.xes";
			Path path = Paths.get(pathtext);
			File xmlLogs = new File(path.toString());
			XesXmlParser parser = new XesXmlParser();
			boolean okParser = parser.canParse(xmlLogs);
		    ArrayList<XLog> logs =  (ArrayList<XLog>)parser.parse(xmlLogs);
		    for(XLog l: logs)
		    {
		    	System.out.println(l.getGlobalEventAttributes());
		    }
			
		}
		catch(Exception e) {
			
			
		}
	}
}
