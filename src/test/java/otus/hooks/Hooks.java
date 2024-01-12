package otus.hooks;

import io.cucumber.java.After;
import otus.di.GuiceScoped;
import javax.inject.Inject;

public class Hooks {
  @Inject
  private GuiceScoped guiceScoped;
  @After
  public void afterEach(){
    if(guiceScoped.driver != null) {
      guiceScoped.driver.close();
      guiceScoped.driver.quit();
    }
  }
}
