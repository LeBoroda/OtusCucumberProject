package otus.pages.implementation;

import com.google.inject.Inject;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.service.GuiceScoped;
import otus.waiters.StandardWaiter;

public class AbsWebPageObject {

  protected EventFiringWebDriver driver;
  protected Actions actions;
  protected StandardWaiter waiter;

  @Inject
  public AbsWebPageObject(GuiceScoped guiceScoped) {
    this.driver = guiceScoped.driver;
    this.actions = new Actions(driver);
    this.waiter = new StandardWaiter(driver);
  }

}
