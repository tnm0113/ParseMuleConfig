package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.MuleApp;
import model.MuleFlow;

public class TestGenerator {
	public void genTestForMuleApp(MuleApp app) {
		try {
			System.out.println("List flow size " + app.getListFlows().size());
			for (int i=0; i<app.getListFlows().size(); i++) {
				MuleFlow flow = app.getListFlows().get(i);
				String testFileName = new StringBuilder().append("Test")
														 .append(flow.getName())
														 .append(".java")
														 .toString();
				BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName));
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
				String className = "public class";
				className += testFileName;
				className += "extends FunctionalMunitSuite {";
				writer.write(className);
				writer.newLine();
				
				writer.write("\t@Test");
				writer.newLine();
				writer.write("\tpublic void Test() throws Exception {");
				writer.newLine();
				writer.write("\t\tString myStringPayload = \"myPayload\";");
				writer.newLine();
				
				String runFlow = "\t\tMuleEvent resultMuleEvent = runFlow(\"";
				runFlow += flow.getName();
				runFlow += "\",testEvent(myStringPayload)";
				writer.write(runFlow);
				writer.newLine();
				
				writer.write("\t\tAssert.assertEquals(myStringPayload, resultMuleEvent.getMessage().getPayload()); ");
				writer.newLine();
				writer.write("\t}");
				writer.newLine();
				writer.write("}");
				
				writer.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
