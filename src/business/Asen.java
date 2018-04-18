package business;

import model.MuleApp;

public class Asen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DomParser parser = new DomParser("hello-world.xml");
		MuleApp app = parser.parseFromXml();
	}

}
