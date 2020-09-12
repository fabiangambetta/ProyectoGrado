package Model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.ParserFactory;

public class CRMLParser {
	String Path;

	public static void main(String[] args) {

		ParseComplianceModel("c:/modelFinal3.crml");

	}

	public static ComplianceModel ParseComplianceModel(String pathtext) {

		try {
			Path path = Paths.get(pathtext);
			ComplianceModel cm = new ComplianceModel();
			File fXmlFile = new File(path.toString());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nListcomplianceobjecttype = doc.getElementsByTagName("complianceobjecttype");
			for (int temp = 0; temp < nListcomplianceobjecttype.getLength(); temp++) {

				Node ncomplianceobjecttype = nListcomplianceobjecttype.item(temp);
				if (ncomplianceobjecttype.getNodeType() == Node.ELEMENT_NODE) {
					cm.getObjectsTypes().add(ParseComplianceObjectType(ncomplianceobjecttype));
				}
			}

			NodeList nListcomplianceobject = doc.getElementsByTagName("complianceobject");
			for (int temp = 0; temp < nListcomplianceobject.getLength(); temp++) {

				Node ncomplianceobject = nListcomplianceobject.item(temp);
				if (ncomplianceobject.getNodeType() == Node.ELEMENT_NODE) {
					cm.getObjects().add(ParseComplianceObject(ncomplianceobject, cm));
				}
			}

			// Cargar todas las áreas
			NodeList nListAreas = doc.getElementsByTagName("compliancearea");
			for (int i = 0; i < nListAreas.getLength(); i++) {
				Node nArea = nListAreas.item(i);
				cm.getAreas().add(ParseArea(nArea, cm));
			}

			NodeList nListRegulations = doc.getElementsByTagName("regulation");
			for (int temp = 0; temp < nListRegulations.getLength(); temp++) {

				Node nRegulation = nListRegulations.item(temp);
				if (nRegulation.getNodeType() == Node.ELEMENT_NODE) {
					cm.getRegulations().add(ParseRegulation(nRegulation));
				}
			}

			NodeList nListRequirements = doc.getElementsByTagName("compliancerequirement");
			for (int temp = 0; temp < nListRequirements.getLength(); temp++) {

				Node nRequirement = nListRequirements.item(temp);
				if (nRequirement.getNodeType() == Node.ELEMENT_NODE) {
					cm.getRequirements().add(ParseRequirement(nRequirement, cm));
				}
			}
			return cm;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static int GetIntorDefault(Element elemento, String value, int defaultValue) {
		String val = elemento.getAttribute(value);
		if (val.equals(""))
			return defaultValue;
		else {
			return Integer.parseInt(val);
		}
	}

	private static ComplianceObject ParseComplianceObject(Node ncomplianceobject, ComplianceModel cm) {
		Element ecomplianceobject = (Element) ncomplianceobject;
		String type = ecomplianceobject.getAttribute("hasTypeOf");
		String[] typeSpl = type.split("\\.");
		return new ComplianceObject(GetIntorDefault(ecomplianceobject, "complianceobjectId", -1),
				ecomplianceobject.getAttribute("name"), cm.getObjectsTypes().get(Integer.parseInt(typeSpl[1])));
	}

	private static ComplianceObjectType ParseComplianceObjectType(Node ncomplianceobjecttype) {
		Element ecomplianceobjecttype = (Element) ncomplianceobjecttype;
		return new ComplianceObjectType(GetIntorDefault(ecomplianceobjecttype, "typeId", -1),
				ecomplianceobjecttype.getAttribute("name"));
	}

	private static Regulation ParseRegulation(Node nRegulation) {
		Element eRegulation = (Element) nRegulation;
		return new Regulation(GetIntorDefault(eRegulation, "regulationId", -1), eRegulation.getAttribute("name"));
	}

	private static ComplianceArea ParseArea(Node nArea, ComplianceModel cm) {
		Element eArea = (Element) nArea;
		ComplianceArea area = new ComplianceArea(Integer.parseInt(eArea.getAttribute("areaId")),
				eArea.getAttribute("name"));

		// Cargar las dimensiones de la noticia
		NodeList nListDimensiones = nArea.getChildNodes();
		for (int j = 0; j < nListDimensiones.getLength(); j++) {
			Node nDimension = nListDimensiones.item(j);
			if (nDimension.getNodeType() == Node.ELEMENT_NODE) {
				area.getCompliancedimensions().add(ParseDimension(nDimension, cm));
			}

		}
		return area;

	}

	private static ComplianceDimension ParseDimension(Node nDimension, ComplianceModel cm) {

		Element eDimension = (Element) nDimension;
		ComplianceDimension dimension = new ComplianceDimension(GetIntorDefault(eDimension, "dimensionId", -1),
				eDimension.getAttribute("name"));
		// Cargar los factores en la dimensión
		NodeList nListFactors = nDimension.getChildNodes();
		for (int k = 0; k < nListFactors.getLength(); k++) {
			Node nFactor = nListFactors.item(k);
			if (nFactor.getNodeType() == Node.ELEMENT_NODE) {
				dimension.getCompliancefactors().add(ParseFactor(nFactor,cm));
			}
		}
		return dimension;
	}

	private static ComplianceFactor ParseFactor(Node nFactor, ComplianceModel cm) {
		Element eFactor = (Element) nFactor;
		ComplianceFactor factor = new ComplianceFactor(GetIntorDefault(eFactor, "factorId", -1),
				eFactor.getAttribute("name"));
		// Cargar los controles del factor
		NodeList nListControls = nFactor.getChildNodes();
		for (int h = 0; h < nListControls.getLength(); h++) {
			Node nControl = nListControls.item(h);
			if (nControl.getNodeType() == Node.ELEMENT_NODE) {
				factor.getCompliancecontrols().add(ParseControl(nControl, cm));
			}
		}
		return factor;
	}

	private static ComplianceControl ParseControl(Node nControl, ComplianceModel cm) {
		Element eControl = (Element) nControl;
		ComplianceControl control = new ComplianceControl(GetIntorDefault(eControl, "controlId", -1),
				eControl.getAttribute("name"));
		// Cargar los control config props en los compliance control
		NodeList nListControlsConfigProp = nControl.getChildNodes();
		for (int m = 0; m < nListControlsConfigProp.getLength(); m++) {
			Node nControlConfigProp = nListControlsConfigProp.item(m);
			if (nControlConfigProp.getNodeType() == Node.ELEMENT_NODE) {
				Element eControlConfigProp = (Element) nControlConfigProp;
				ControlConfigurationProperty controlconfigprop = new ControlConfigurationProperty(
						eControlConfigProp.getAttribute("name"), eControlConfigProp.getAttribute("type"));
				control.getControlconfigurationproperties().add(controlconfigprop);
			}
		}
		String objectTypes = eControl.getAttribute("apliesTo");
		String[] objectTypesarray = objectTypes.split("//");
		for (int i = 1; i < objectTypesarray.length; i++) {
				int objectTypesindex = Integer.parseInt(objectTypesarray[i].split("\\.")[1]);
				control.getComplianceobjecttypes().add(cm.getObjectsTypes().get(objectTypesindex));
		}
		return control;
	}

	private static ComplianceRequirement ParseRequirement(Node nRequirement, ComplianceModel cm) {
		Element eRequirement = (Element) nRequirement;
		String BaseOn = eRequirement.getAttribute("basedOn");
		String[] BaseOnArray = BaseOn.split("/");
		String compliancearea = BaseOnArray[2];
		String compliancedimension = BaseOnArray[3];
		String compliancefactor = BaseOnArray[4];
		String compliancecontrol = BaseOnArray[5];
		int complianceareaindex = Integer.parseInt(compliancearea.split("\\.")[1]);
		int complianedimensionindex = Integer.parseInt(compliancedimension.split("\\.")[1]);
		int compliancefactorindex = Integer.parseInt(compliancefactor.split("\\.")[1]);
		int compliancecontrolindex = Integer.parseInt(compliancecontrol.split("\\.")[1]);
		ComplianceArea areabaseon = cm.getAreas().get(complianceareaindex);
		ComplianceDimension dimensionbaseon = areabaseon.getCompliancedimensions().get(complianedimensionindex);
		ComplianceFactor factorbaseon = dimensionbaseon.getCompliancefactors().get(compliancefactorindex);
		ComplianceControl controlbaseon = factorbaseon.getCompliancecontrols().get(compliancecontrolindex);
		ComplianceRequirement req = new ComplianceRequirement(0, eRequirement.getAttribute("name"), controlbaseon);
		NodeList nListControlConfig = nRequirement.getChildNodes();
		for (int i = 0; i < nListControlConfig.getLength(); i++) {
			Node nControlConfig = nListControlConfig.item(i);
			if (nControlConfig.getNodeType() == Node.ELEMENT_NODE) {
				Element eControlConfig = (Element) nControlConfig;
				String config = eControlConfig.getAttribute("configures");
				String[] ApplytoConfig = config.split("/");
				int configpropindex = Integer.parseInt(ApplytoConfig[6].split("\\.")[1]);
				ControlConfigurationProperty controlconfigpropbaseon = controlbaseon.getControlconfigurationproperties()
						.get(configpropindex);
				ControlConfigurationPropertyValue controlconfigpropvalue = new ControlConfigurationPropertyValue(
						eControlConfig.getAttribute("value"), controlconfigpropbaseon);
				req.getControlconfigurationpropertiesvalue().add(controlconfigpropvalue);

			}
		}
		String orginatesFrom = eRequirement.getAttribute("orginatesFrom");
		String[] regulationsArray = orginatesFrom.split("//");
		for (int i = 1; i < regulationsArray.length; i++) {
				int regulationindex = Integer.parseInt(regulationsArray[i].split("\\.")[1].trim());
				req.getRegulations().add(cm.getRegulations().get(regulationindex));
		}

		String appliesTocomplianceobjecttype = eRequirement.getAttribute("appliesTocomplianceobjecttype");
		String[] complianceobjecttypeArray = appliesTocomplianceobjecttype.split("//");
		for (int i = 1; i < complianceobjecttypeArray.length; i++) {
			int complianceobjecttypeindex = Integer.parseInt(complianceobjecttypeArray[i].split("\\.")[1].trim());
			req.getComplianceobjecttypes().add(cm.getObjectsTypes().get(complianceobjecttypeindex));
		}

		String appliesTocomplianceobject = eRequirement.getAttribute("appliesTocomplianceobject");
		String[] complianceobjectArray = appliesTocomplianceobject.split("//");
		for (int i = 1; i < complianceobjectArray.length; i++) {
			int complianceobjectindex = Integer.parseInt(complianceobjectArray[i].split("\\.")[1].trim());
			req.getComplianceobjects().add(cm.getObjects().get(complianceobjectindex));
		}

		return req;
	}
}
