package Model;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CRMLParser {
	String Path;
	
	public static void main(String[] args) {
		parser();

	}
	
	public static void parser()
	{
	    ArrayList<ComplianceArea> listAreas = new ArrayList<ComplianceArea>();
		try {
			File fXmlFile = new File("c:/modelFinal3.crml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			ArrayList<ComplianceRequirement> Requierements = new ArrayList<ComplianceRequirement>();
			doc.getDocumentElement().normalize();
			
			//Cargar todas las áreas
			NodeList nListAreas = doc.getElementsByTagName("compliancearea");
			for(int i=0; i< nListAreas.getLength();i++)
			{
				Node nArea = nListAreas.item(i);
				Element eArea = (Element) nArea;
				ComplianceArea area = new ComplianceArea();
				area.name = eArea.getAttribute("name"); 
				area.areaid = Integer.parseInt(eArea.getAttribute("areaId"));
				listAreas.add(area);
				//Cargar las dimensiones de la noticia
				NodeList nListDimensiones =  nArea.getChildNodes();
				for(int j=0; j < nListDimensiones.getLength();j++ )
				{
					Node nDimension = nListDimensiones.item(j);
					if(nDimension.getNodeType() == Node.ELEMENT_NODE) {
						Element eDimension= (Element) nDimension;
						ComplianceDimension dimension = new ComplianceDimension();
						dimension.name = eDimension.getAttribute("name");
						dimension.dimensionid = Integer.parseInt(eDimension.getAttribute("dimensionId"));
						area.compliancedimensions.add(dimension);
						//Cargar los factores en la dimensión
						
						NodeList nListFactors = nDimension.getChildNodes();
						for(int k=0; k < nListFactors.getLength();k++)
						{
							Node nFactor = nListFactors.item(k);
							if(nFactor.getNodeType() == Node.ELEMENT_NODE) {
								Element eFactor= (Element) nFactor;
								ComplianceFactor factor = new ComplianceFactor();
								factor.name = eFactor.getAttribute("name");
								factor.factorid = Integer.parseInt(eFactor.getAttribute("factorId"));
								dimension.compliancefactors.add(factor);
								//Cargar los controles del factor
								NodeList nListControls = nFactor.getChildNodes();
								for(int h=0; h< nListControls.getLength();h++)
								{
									Node nControl = nListControls.item(h);
									if(nControl.getNodeType() == Node.ELEMENT_NODE) {
										Element eControl= (Element) nControl;
										ComplianceControl control = new ComplianceControl();
										control.name = eControl.getAttribute("name");
										factor.compliancecontrols.add(control);
										//Cargar los control config props en los compliance control
										NodeList nListControlsConfigProp = nControl.getChildNodes();
										for( int m=0; m < nListControlsConfigProp.getLength();m++)
										{
											Node nControlConfigProp = nListControlsConfigProp.item(m);
											if(nControlConfigProp.getNodeType() == Node.ELEMENT_NODE) {
												Element eControlConfigProp= (Element) nControlConfigProp;
												ControlConfigurationProperty controlconfigprop = new ControlConfigurationProperty();
												controlconfigprop.name = eControlConfigProp.getAttribute("name");
												control.controlconfigurationproperties.add(controlconfigprop);
											}
										}
									}
									
								}
							}
						}
					}
					
				}	
			}
			
			NodeList nListRequirements = doc.getElementsByTagName("compliancerequirement");
			for (int temp = 0; temp < nListRequirements.getLength(); temp++) {

				Node nRequirement = nListRequirements.item(temp);
				if (nRequirement.getNodeType() == Node.ELEMENT_NODE) {
					Element eRequirement = (Element) nRequirement;
					ComplianceRequirement req = new ComplianceRequirement();
					req.name = eRequirement.getAttribute("name");
					Requierements.add(req);
					
					//Asociar Compliance Control al requirement
					String BaseOn = eRequirement.getAttribute("basedOn");
					String [] BaseOnArray = BaseOn.split("/");
					String compliancearea = BaseOnArray[2];
					String compliancedimension = BaseOnArray[3];
					String compliancefactor = BaseOnArray[4];
					String compliancecontrol = BaseOnArray[5];
					
					int complianceareaindex = Integer.parseInt(compliancearea.split("\\.")[1]);
					int complianedimensionindex = Integer.parseInt(compliancedimension.split("\\.")[1]);
					int compliancefactorindex = Integer.parseInt(compliancefactor.split("\\.")[1]);
					int compliancecontrolindex = Integer.parseInt(compliancecontrol.split("\\.")[1]); 
					
					ComplianceArea areabaseon = listAreas.get(complianceareaindex);
					ComplianceDimension dimensionbaseon = areabaseon.compliancedimensions.get(complianedimensionindex);
					ComplianceFactor factorbaseon = dimensionbaseon.compliancefactors.get(compliancefactorindex);
					ComplianceControl controlbaseon = factorbaseon.compliancecontrols.get(compliancecontrolindex);
					
					req.controlcompliance = controlbaseon;
					
					
					NodeList nListControlConfig =  nRequirement.getChildNodes();
					for(int i = 0; i < nListControlConfig.getLength(); i++)
					{
						Node nControlConfig = nListControlConfig.item(i);
						if (nControlConfig.getNodeType() == Node.ELEMENT_NODE) {
							Element eControlConfig = (Element) nControlConfig;
							String config = eControlConfig.getAttribute("configures");
							String [] ApplytoConfig =  config.split("/");
							
							
							int configpropindex =  Integer.parseInt(ApplytoConfig[6].split("\\.")[1]);
							ControlConfigurationProperty controlconfigpropbaseon = controlbaseon.controlconfigurationproperties.get(configpropindex);
							
							ControlConfigurationPropertyValue controlconfigpropvalue = new ControlConfigurationPropertyValue();
							controlconfigpropvalue.value = eControlConfig.getAttribute("value");
							req.controlconfigurationpropertiesvalue.add(controlconfigpropvalue);
							//controlconfigpropvalue.controlconfigurationproperties.add(controlconfigpropbaseon);
							
							
							System.out.println("Config: " + config);
							ControlConfigurationProperty controlConfig = new ControlConfigurationProperty();
						}
					}

					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
