package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import otus.pages.OnlineCoursePage;

public class OnlineCoursePageSteps {
  @Inject
  OnlineCoursePage onlineCoursePage;

  @Если("Открываю страницу онлайн курсов")
  public void openOnlineCoursePage() {
    onlineCoursePage.open();
  }

  @Тогда("Ищу и вывожу в консоль самый дешевый и самый дорогой курс")
  public void searchCheapCourse() {
    onlineCoursePage.findCheapAndExpensiveCourseInfo();
  }
}
