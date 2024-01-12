package otus.pages;

import otus.annotations.Path;
import otus.components.CourseTileComponent;
import otus.data.CourseTitleData;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import otus.di.GuiceScoped;
import otus.pages.implementation.AbsBasePage;

import javax.inject.Inject;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void findCourseByTitle(CourseTitleData courseTitle) {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(new GuiceScoped());
    courseTile.getCourseByTitle(courseTitle.getName());
  }

  public void getLatestCourse() {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(new GuiceScoped());
    courseTile.getCourseByDate("LATEST");
  }

  public void getEarliestCourse() {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(new GuiceScoped());
    courseTile.getCourseByDate("EARLIEST");
  }

}
