import static org.junit.Assert.*;

import org.junit.Test;


public class MailBoxTest {

	@Test
	public void testCheckPasscode() {
		Mailbox mailtest= new Mailbox( "123",  "12a3");
		boolean result=mailtest.checkPasscode("123");
		assertEquals(true, result);
	}
	@Test
	public void test() {
		Mailbox mailtest= new Mailbox( "123",  "12a3");
		boolean result=mailtest.checkPasscode("123");
		assertEquals(true, result);
	}
	

}
