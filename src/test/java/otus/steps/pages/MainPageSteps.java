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

  @Тогда("Ищу страницу курса {string}")
  public void findCourseByTitle(String courseTitle) {
    mainPage.findCourseByTitle(courseTitle);
  }

  @Тогда("Ищу курсы стартующие с {string}")
  public void findCoursesByTitle(String dateOfStart) {
    mainPage.getCourseNameAndStartDates(dateOfStart);
  }

}
