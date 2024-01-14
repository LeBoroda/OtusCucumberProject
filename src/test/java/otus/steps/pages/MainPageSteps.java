package otus.steps.pages;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import otus.pages.MainPage;
import javax.inject.Inject;

public class MainPageSteps {
  @Inject
  private MainPage mainPage;

  @Если("Открываю главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @Тогда("Ишу страницу курса {string}")
  public void findCourseByTitle(String courseTitle) {
  }

}
