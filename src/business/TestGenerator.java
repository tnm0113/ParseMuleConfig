package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.MuleApp;

public class TestGenerator {
	public void genTestForMuleApp(MuleApp app) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("MyTest.java"));
			//import libs
			writer.write("import org.junit.Assert;");
			writer.newLine();
			writer.write("import org.junit.Test;");
			writer.newLine();
			writer.write("import org.mule.api.MuleEvent;");
			writer.newLine();
			writer.write("import org.mule.munit.runner.functional.FunctionalMunitSuite;");
			writer.newLine();
			
			writer.newLine();
			
			//main 
			writer.write("public class MyTest extends FunctionalMunitSuite {");
			writer.newLine();
			writer.write("\t@Test");
			writer.newLine();
			writer.write("\tpublic void Test() throws Exception {");
			writer.newLine();
			writer.write("\t\tString myStringPayload = \"myPayload\";");
			writer.newLine();
			writer.write("\t\tMuleEvent resultMuleEvent = runFlow(\"echoFlow\", testEvent(myStringPayload)); ");
			writer.newLine();
			writer.write("\t\tAssert.assertEquals(myStringPayload, resultMuleEvent.getMessage().getPayload()); ");
			writer.newLine();
			writer.write("\t}");
			writer.newLine();
			writer.write("}");
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
