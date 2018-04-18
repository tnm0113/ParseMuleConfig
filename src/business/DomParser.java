package business;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.MuleApp;
import model.MuleFlow;
import model.MuleHttpListener;
import model.MuleHttpListenerConfig;
import model.MulePayload;

public class DomParser {
	private String fileName;
	public DomParser(String name) {
		fileName = name;
	}
	
	public MuleApp parseFromXml(){
		MuleApp app = new MuleApp();
		try {
			File inputFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			System.out.println("----------------------------");
			System.out.println("Flow element: ");
			NodeList flowList = doc.getElementsByTagName("flow");
			MuleFlow flow = new MuleFlow();
			for (int temp = 0; temp < flowList.getLength(); temp++) {
				Node nNode = flowList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					flow.setName(eElement.getAttribute("name"));
					flow.setDocid(eElement.getAttribute("doc:id"));
					app.setFlow(flow);
					System.out.println("Flow name : " 
							+ eElement.getAttribute("name"));
					System.out.println("Flow doc id : " 
							+ eElement.getAttribute("doc:id"));
				}
			}
			
			System.out.println("----------------------------");
			System.out.println("Http Listener Config element: ");
			NodeList httpListenerConnection = doc.getElementsByTagName("http:listener-connection");
			MuleHttpListenerConfig httpListenerConfig = new MuleHttpListenerConfig();
			for (int temp = 0; temp < httpListenerConnection.getLength(); temp++) {
				Node nNode = httpListenerConnection.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					httpListenerConfig.setHost(eElement.getAttribute("host"));
					httpListenerConfig.setPort(Integer.parseInt(eElement.getAttribute("port")));
					app.setListenerConfig(httpListenerConfig);
					System.out.println("Host : " 
							+ eElement.getAttribute("host"));
					System.out.println("Port : " 
							+ eElement.getAttribute("port"));
					
				}
			}
			
			System.out.println("----------------------------");
			System.out.println("Http Listener element: ");
			NodeList httpListenerList = doc.getElementsByTagName("http:listener");
			MuleHttpListener httpListener = new MuleHttpListener();
			for (int temp = 0; temp < httpListenerList.getLength(); temp++) {
				Node nNode = httpListenerList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					httpListener.setName(eElement.getAttribute("name"));
					httpListener.setPath(eElement.getAttribute("path"));
					flow.setHttpListener(httpListener);
					System.out.println("Name : " 
							+ eElement.getAttribute("name"));
					System.out.println("Path : " 
							+ eElement.getAttribute("path"));
				}
			}
			
			System.out.println("----------------------------");
			System.out.println("Payload element: ");
			NodeList payloadList = doc.getElementsByTagName("set-payload");
			MulePayload payload = new MulePayload();
			for (int temp = 0; temp < payloadList.getLength(); temp++) {
				Node nNode = payloadList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					payload.setValue(eElement.getAttribute("value"));
					payload.setMimeType(eElement.getAttribute("mimeType"));
					flow.setPayload(payload);
					System.out.println("Value : " 
							+ eElement.getAttribute("value"));
					System.out.println("Mime Type : " 
							+ eElement.getAttribute("mimeType"));
				}
			}
			app.setFlow(flow);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return app;
	}
}
