package mainpage;

import otus.annotations.Driver;
import otus.annotations.Page;
import otus.data.CourseTitleData;
import otus.extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import otus.pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainPage_Test {

  @Driver
  private WebDriver driver;
  @Page
  private MainPage mainPage;


  @Test
  public void coursesTitleSortTest() {
    mainPage
        .open()
        .findCourseByTitle(CourseTitleData.APACHEKAFKA);
  }

  @Test
  public void latestCourseChoiceTest() {
    mainPage
        .open()
        .getLatestCourse();
  }

  @Test
  public void earliestCourseChoiceTest() {
    mainPage
        .open()
        .getEarliestCourse();
  }
}
