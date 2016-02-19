import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @Test
  public void getName_returnsName_true() {
    Word testWord = new Word("Canine");
    assertEquals("Canine", testWord.getName());
  }

  @Test
  public void getId_returnsWordId() {
    Word testWord = new Word("Canine");
    assertTrue(Word.all().size() == testWord.getId());
  }

  @Test
  public void getTasks_initiallyReturnsEmptyArrayList() {
    Word testWord = new Word("Canine");
    assertTrue(testWord.getDefinitions() instanceof ArrayList);
  }

  @Test
  public void all_returnsAllInstancesOfTask_true() {
    Word firstWord = new Word("Canine");
    Word secondWord = new Word("Canine");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void clear_removesAllWordInstancesFromMemory() {
    Word testWord = new Word("Canine");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void find_returnsWordWithSameId() {
    Word testWord = new Word("Canine");
    assertEquals(Word.find(testWord.getId()), testWord);
  }
  
  @Test
  public void addTask_addsTaskToList() {
    Word testWord = new Word("Canine");
    Definition testDefinition = new Definition("a dog");
    testWord.addDefinition(testDefinition);
    assertTrue(testWord.getDefinitions().contains(testDefinition));
  }
}
