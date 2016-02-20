import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  
  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Dictionary");
  }

  @Test
  public void WordIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Word"));
    fill("#name").with("Canine");
    submit(".btn");
    assertThat(pageSource()).contains("Your word has been saved.");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/new");
    fill("#name").with("Canine");
    submit(".btn");
    click("a", withText("View All Words"));
    assertThat(pageSource()).contains("Canine");
  }

  @Test
  public void wordDescriptionFormIsDisplayed() {
    goTo("http://localhost:4567/new");
    fill("#name").with("Feline");
    submit(".btn");
    click("a", withText("View All Words"));
    click("a", withText("Feline"));
    click("a", withText("Add a new definition"));
    assertThat(pageSource()).contains("Add a new definition to your word Feline");
  }

  @Test
  public void DescriptionssIsAddedAndDisplayed() {
    goTo("http://localhost:4567/new");
    fill("#name").with("simian");
    submit(".btn");
    click("a", withText("View All Words"));
    click("a", withText("simian"));
    click("a", withText("Add a new definition"));
    fill("#description").with("a monkey");
    submit(".btn");
    assertThat(pageSource()).contains("a monkey");
  }
}
