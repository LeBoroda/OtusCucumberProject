package otus.di;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.factories.WebDriverFactory;

@ScenarioScoped
public class GuiceScoped {
  public EventFiringWebDriver driver = new WebDriverFactory().createDriver();
}
