import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class MyTest extends FunctionalMunitSuite {
	@Test
	@public void Test() throws Exception {
		String myStringPayload = "myPayload";
		MuleEvent resultMuleEvent = runFlow("echoFlow", testEvent(myStringPayload)); 
		Assert.assertEquals(myStringPayload, resultMuleEvent.getMessage().getPayload()); 
	}
}