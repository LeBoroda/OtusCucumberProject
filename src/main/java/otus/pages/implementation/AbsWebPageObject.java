package otus.pages.implementation;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.di.GuiceScoped;
import otus.waiters.StandardWaiter;
import javax.inject.Inject;

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
