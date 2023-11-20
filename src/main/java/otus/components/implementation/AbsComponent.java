package otus.components.implementation;

import com.google.inject.Inject;
import otus.pages.implementation.AbsWebPageObject;
import otus.service.GuiceScoped;

public abstract class AbsComponent<T> extends AbsWebPageObject {
  @Inject
  public AbsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
}
