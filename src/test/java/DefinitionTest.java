import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class DefinitionTest {

	// @Rule
	// public ClearRule clearRule = new ClearRule();

	@Test
	public void newDefinition_instatiateCorrectly_true() {
		Definition testDefinition = new Definition("a dog.");
		assertEquals(true, testDefinition instanceof Definition);
	}

	 @Test
  	public void newDefinition_displayADefinition() {
    Definition testDefinition = new Definition("a dog.");
    assertEquals("a dog.", testDefinition.getDescription());
  }

}
