import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class _ConnectionTest {

@Test
public void newConnectionShouldBeConnected() {
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	
	verify(phone).speak("Enter mailbox number followed by #");
	assertTrue(conn.isConnected());
}


@Test
public void whenDialInAConnectedStateItShouldChangeToRecording() {
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox mailbox = mock(Mailbox.class);
	
	when(system.findMailbox("1")).thenReturn(mailbox);
	when(mailbox.getGreeting()).thenReturn("Hola mailbox");
	
	
	conn.dial("1");
	conn.dial("#");
	
	verify(phone).speak("Hola mailbox");
	assertTrue(conn.isRecording());
	
}
@Test
public void getIntoMailBoxAndSave() {
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	
	verify(phone).speak("Enter 1 to listen to the current message\n"
	         + "Enter 2 to save the current message\n"
	         + "Enter 3 to delete the current message\n"
	         + "Enter 4 to return to the main menu");
}
@Test
public void listenToMessageAndDelete() {
	//escuchar mensage eliminado
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	Message mensaje= new Message("hola");
	currentMailbox.addMessage(mensaje);
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("3");


	
	
	verify(phone,times(2)).speak("Enter 1 to listen to the current message\n"
	         + "Enter 2 to save the current message\n"
	         + "Enter 3 to delete the current message\n"
	         + "Enter 4 to return to the main menu");
}
@Test
public void fromMessageMenuReturnToMEnu() {
	//escuchar mensage return menu
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	Message mensaje= new Message("hola");
	currentMailbox.addMessage(mensaje);
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("4");


	
	
	verify(phone,times(2)).speak( "Enter 1 to listen to your messages\n"
	         + "Enter 2 to change your passcode\n"
	         + "Enter 3 to change your greeting");
}

@Test
public void listenToCurrentMessageAndSave() {
	//escuchar mensage guardado
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	Message mensaje= new Message("hola");
	currentMailbox.addMessage(mensaje);
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("2");


	
	
	verify(phone,times(2)).speak("Enter 1 to listen to the current message\n"
	         + "Enter 2 to save the current message\n"
	         + "Enter 3 to delete the current message\n"
	         + "Enter 4 to return to the main menu");
}

@Test
public void changeGreeting() {
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");

	conn.dial("3");
	
	verify(phone).speak("Record your greeting, then press the # key");
}


@Test
public void whenDialInAConnectedStateAndNoMailboxFoundItShouldShowAnErrorMessage() {
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	
	when(system.findMailbox("10")).thenReturn(null);
	
	conn.dial("10");
	conn.dial("#");
	
	verify(phone).speak("Incorrect mailbox number. Try again!");
	
}
@Test
public void test1() {
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	String mailboxText = "Enter 1 to listen to your messages\n"
	+ "Enter 2 to change your passcode\n"
	+ "Enter 3 to change your greeting";
	
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	
	assertTrue(conn.isInMailBoxMenu());
	verify(phone).speak(mailboxText);

}

@Test
public void test2() {
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	String mailboxText = "Incorrect passcode. Try again!";
	
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("2")).thenReturn(false);
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("2");
	conn.dial("#");
	
	assertFalse(conn.isInMailBoxMenu());
	verify(phone).speak(mailboxText);

}

@Test
public void getIntoChangePasscodeOption(){
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("7");
	verify(phone).speak( "Enter 1 to listen to your messages\n"
	         + "Enter 2 to change your passcode\n"
	         + "Enter 3 to change your greeting");
}
@Test
public void getIntoChangeGreedingOption(){
	
	MailSystem system = mock(MailSystem.class);
	Telephone phone = mock(Telephone.class);
	Connection conn = new Connection(system, phone);
	Mailbox currentMailbox = mock(Mailbox.class);
	
	when(system.findMailbox("1")).thenReturn(currentMailbox);
	when(currentMailbox.checkPasscode("1")).thenReturn(true);
	
	
	conn.dial("1");
	conn.dial("#");
	conn.dial("1");
	conn.dial("#");
	conn.dial("2");
	verify(phone).speak("Enter new passcode followed by the # key");
}
} 