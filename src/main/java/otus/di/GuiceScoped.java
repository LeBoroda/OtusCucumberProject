package otus.di;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.factories.WebDriverFactory;
import java.util.Locale;

@ScenarioScoped
public class GuiceScoped {
  public EventFiringWebDriver driver = null;

  public void choseBrowser(String browserName){
    System.setProperty("browser", browserName.toLowerCase(Locale.ROOT));
    this.driver = new WebDriverFactory().createDriver();
  }
}
