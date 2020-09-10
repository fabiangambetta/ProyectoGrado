package XES;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.Random;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XExtendedEvent;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryBufferedImpl;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XAttributeTimestampImpl;
import org.deckfour.xes.model.impl.XEventImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;
import org.deckfour.xes.out.XesXmlSerializer;

public class XMLGenerator
{
	public static Random r;
	public enum Activities {
		GetAvailableDatesAGESIC,
		GetAvailableDatesDNIC,
		ConfirmAppointmentAGESIC,		
		ConfirmAppointmentDNIC,
		JudicialRecordsDNIC,
		JudicualRecordsResponseDNTP,
		NotifyAppointmentCancelationDNIC,
		NotifyAppointmentResultDNIC,
		End,
	}
	
	private static Activities GetNextActivity(Activities prev_act, float random) {
		
		switch (prev_act) {
		case GetAvailableDatesAGESIC:
			if(random>.9)
			{
				return Activities.ConfirmAppointmentAGESIC;
			}
			return Activities.GetAvailableDatesDNIC;
			
		case GetAvailableDatesDNIC:
			if(random>.9)
			{
				return Activities.GetAvailableDatesAGESIC;
			}
			return Activities.ConfirmAppointmentAGESIC;
			
		case ConfirmAppointmentAGESIC:
			if(random>.9)
			{
				return Activities.GetAvailableDatesAGESIC;
			}
			return Activities.ConfirmAppointmentDNIC;
		case ConfirmAppointmentDNIC:
			if(random>.9)
			{
				return Activities.GetAvailableDatesAGESIC;
			}
			if(random>.8)
			{
				return Activities.End;
			}
			return Activities.JudicialRecordsDNIC;
		case JudicialRecordsDNIC:
			if(random>.9)
			{
				return Activities.ConfirmAppointmentDNIC;
			}
			if(random>.7)
			{
				return Activities.NotifyAppointmentCancelationDNIC;
			}
			return Activities.JudicualRecordsResponseDNTP;
		case JudicualRecordsResponseDNTP:
			if(random>.8)
			{
				return Activities.NotifyAppointmentCancelationDNIC;
			}
			return Activities.NotifyAppointmentResultDNIC;
		case NotifyAppointmentCancelationDNIC:
			return Activities.End;
		case NotifyAppointmentResultDNIC:
			return Activities.End;
		default:
			return Activities.End;
		}
		
	}
	
	private static XTrace GenerateRandomTrace(int traceNumber) {
		Activities current_activity = Activities.GetAvailableDatesAGESIC;
		
		XAttributeMap attributeMap;
		attributeMap = new XAttributeMapImpl();
		XAttribute value = new XAttributeLiteralImpl("concept:name","Trace "+ traceNumber);
		attributeMap.put("concept:name",value );

		
		;
		XTrace t= new XTraceImpl(attributeMap);
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2020, 1, 1); 
		cal.add(Calendar.SECOND, r.nextInt(60*24*30*5*60));
		while(current_activity!= Activities.End)
		{
			XEvent e = GenerateXEvent(current_activity, cal);
			t.add(e);
			current_activity= GetNextActivity(current_activity, XMLGenerator.r.nextFloat());
		}
		if(r.nextFloat()>.5)
		{
			if(t.size()>=2)
				t.remove(r.nextInt(t.size()-1));
		}
		return t;
	}
	private static String ActivityName(Activities act) {
		switch (act) {
		case GetAvailableDatesAGESIC:
			return "Get Available Dates AGESIC";
		case GetAvailableDatesDNIC:
			return "Get Available Dates DNIC";
		case ConfirmAppointmentAGESIC:
			return "Confirm Appointment AGESIC";
		case ConfirmAppointmentDNIC:
			return "Confirm Appointment DNIC";
		case JudicialRecordsDNIC:
			return "Judicial Records DNIC";
		case JudicualRecordsResponseDNTP:
			return "Judicial Records Response DNTP";
		case NotifyAppointmentCancelationDNIC:
			return "Notify Appointment Cancelation DNIC";
		case NotifyAppointmentResultDNIC:
			return "Notify Appointment Result DNIC";
		default:
			return "";
		}
	}
	
	
	private static String FromParticipant(Activities act) {
		switch (act) {
		case GetAvailableDatesAGESIC:
			return "AGESIC";
		case GetAvailableDatesDNIC:
			return "DNIC";
		case ConfirmAppointmentAGESIC:
			return "AGESIC";
		case ConfirmAppointmentDNIC:
			return "DNIC";
		case JudicialRecordsDNIC:
			return "DNIC";
		case JudicualRecordsResponseDNTP:
			return "DNTP";
		case NotifyAppointmentCancelationDNIC:
			return "DNIC";
		case NotifyAppointmentResultDNIC:
			return "DNIC";
		default:
			return "";
		}
	}
	
	private static String ToParticipant(Activities act) {
		switch (act) {
		case GetAvailableDatesAGESIC:
			return "DNIC";
		case GetAvailableDatesDNIC:
			return "AGESIC";
		case ConfirmAppointmentAGESIC:
			return "DNIC";
		case ConfirmAppointmentDNIC:
			return "AGESIC";
		case JudicialRecordsDNIC:
			return "DNTP";
		case JudicualRecordsResponseDNTP:
			return "DNIC";
		case NotifyAppointmentCancelationDNIC:
			return "AGESIC";
		case NotifyAppointmentResultDNIC:
			return "AGESIC";
		default:
			return "";
		}
	}
	
	private static XEvent GenerateXEvent(Activities act,Calendar cal) {
		XAttributeMap attributeMap;
		GetNewDate(act, cal);
		Date date = cal.getTime();
		attributeMap = new XAttributeMapImpl();
		XFactoryBufferedImpl x = new XFactoryBufferedImpl();
		XAttribute value =x.createAttributeLiteral("concept:name", ActivityName(act), XConceptExtension.instance());
		attributeMap.put("concept:name",value);
		XAttribute value2 = new XAttributeLiteralImpl("collab:fromParticipant",FromParticipant(act));
		attributeMap.put("collab:fromParticipant",value2);
		XAttribute value3 = new XAttributeLiteralImpl("collab:toParticipant",ToParticipant(act));
		attributeMap.put("collab:toParticipant",value3);
		XAttribute value4 =x.createAttributeTimestamp("time:timestamp",date, XTimeExtension.instance());
		//XAttribute value4 = new XAttributeTimestampImpl("time:timestamp",date);
		attributeMap.put("time:timestamp",value4);
		XEvent e = new XEventImpl(attributeMap);
		XExtendedEvent eve = new XExtendedEvent(e);
		return eve;
	}
	
	
	private static void GetNewDate(Activities act,Calendar cal) {
		
		switch (act) {
		case GetAvailableDatesDNIC:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(300));
			break;
		case ConfirmAppointmentAGESIC:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(60*300));
			break;
		case ConfirmAppointmentDNIC:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(300));
			break;
		case JudicialRecordsDNIC:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(60*300));
			break;
		case JudicualRecordsResponseDNTP:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(60*60*24));
			break;
		case NotifyAppointmentCancelationDNIC:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(60*60*24));
			break;
		case NotifyAppointmentResultDNIC:
			cal.add(Calendar.SECOND, XMLGenerator.r.nextInt(60*60*24));
			break;
		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		GenerateXML();

	}

	public static void GenerateXML() {
		XMLGenerator.r= new Random();
		Date date = new Date();
		int mili = (int) (date.getTime() % 1000);
		mili=  mili<0 ? mili+1000 : mili;
		XMLGenerator.r.setSeed(mili);
		XAttributeMap attributeMap= new XAttributeMapImpl();
		XLog log= new XLogImpl(attributeMap);
		for (int i = 0; i < 10000; i++) {
			log.add(GenerateRandomTrace(i));
		}
		XesXmlSerializer file = new XesXmlSerializer();
		try {
			File yourFile = new File("output"+mili+".xes");
			if(!yourFile.exists()) {
				yourFile.createNewFile(); // if file already exists will do nothing
			}				 
			OutputStream oFile = new FileOutputStream(yourFile, false);
			file.serialize(log, oFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
