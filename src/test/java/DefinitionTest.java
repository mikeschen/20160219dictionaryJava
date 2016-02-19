import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class PingPongTest {

  @Test
  public void isPingPong_arrayPing() {
    PingPong testPingPong = new PingPong();
    ArrayList<Object> testArray = new ArrayList<Object>();
    testArray.add(1);
    testArray.add(2);
    testArray.add("ping");
    assertEquals(testArray, testPingPong.isPingPong(3));
  }
}
