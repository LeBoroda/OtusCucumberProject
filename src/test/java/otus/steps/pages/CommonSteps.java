package otus.steps.pages;

import io.cucumber.java.ru.Пусть;

public class CommonSteps {

  @Пусть("Открыт браузер")
  public void openBrowser(){
    System.out.println("Открываем браузер");
  }
}
