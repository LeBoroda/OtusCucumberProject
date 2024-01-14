package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Дано;
import otus.di.GuiceScoped;

public class CommonSteps {

  @Inject
  private GuiceScoped guiceScoped;

  @Дано("Открываю браузер {string}")
  public void openBrowser(String browserName) {
    guiceScoped.choseBrowser(browserName);
  }
}
