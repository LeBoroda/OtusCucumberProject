package otus.components.implementation;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.di.GuiceScoped;
import otus.pages.implementation.AbsWebPageObject;

import javax.inject.Inject;

public abstract class AbsComponent<T> extends AbsWebPageObject {
  @Inject
  public AbsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
}
