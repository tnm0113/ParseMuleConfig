import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParser {
	private MuleFlow flow;
	private MuleHttpListenerConfig httpListenerConfig;

	public static void main(String[] args) {
		try {
			File inputFile = new File("hello-world.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			System.out.println("----------------------------");
			System.out.println("Flow element: ");
			NodeList flowList = doc.getElementsByTagName("flow");

			for (int temp = 0; temp < flowList.getLength(); temp++) {
				Node nNode = flowList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Flow name : " 
							+ eElement.getAttribute("name"));
					System.out.println("Flow doc id : " 
							+ eElement.getAttribute("doc:id"));
				}
			}
			
			System.out.println("----------------------------");
			System.out.println("Http Listener Config element: ");
			NodeList httpListenerConnection = doc.getElementsByTagName("http:listener-connection");

			for (int temp = 0; temp < httpListenerConnection.getLength(); temp++) {
				Node nNode = httpListenerConnection.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Host : " 
							+ eElement.getAttribute("host"));
					System.out.println("Port : " 
							+ eElement.getAttribute("port"));
					
				}
			}
			
			System.out.println("----------------------------");
			System.out.println("Payload element: ");
			NodeList payload = doc.getElementsByTagName("set-payload");

			for (int temp = 0; temp < payload.getLength(); temp++) {
				Node nNode = payload.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Value : " 
							+ eElement.getAttribute("value"));
					System.out.println("Mime Type : " 
							+ eElement
							.getAttribute("mimeType"));
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
