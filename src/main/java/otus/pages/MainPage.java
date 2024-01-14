package otus.pages;

import otus.annotations.Path;
import otus.components.CourseTileComponent;
import otus.components.popups.CookiesPopUpComponent;
import otus.data.CourseTitleData;
import otus.di.GuiceScoped;
import otus.pages.implementation.AbsBasePage;
import com.google.inject.Inject;
import java.util.Random;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
  @Inject
  private CookiesPopUpComponent cookiesPopUpComponent;
  @Inject
  private CourseTileComponent courseTileComponent;

  public void findCourseByTitle(CourseTitleData courseTitle) {
    cookiesPopUpComponent.closeCookiesPopup();
    courseTileComponent.getCourseByTitle(courseTitle.getName());
  }
  public void findCourseByTitle(String courseTitle) {
    findCourseByTitle(courseTileComponent.choseCourseTitle(courseTitle));
  }
  public void getLatestCourse() {
    cookiesPopUpComponent.closeCookiesPopup();
    courseTileComponent.getCourseByDate("LATEST");
  }

  public void getEarliestCourse() {
    cookiesPopUpComponent.closeCookiesPopup();
    CourseTileComponent courseTile = new CourseTileComponent(new GuiceScoped());
    courseTile.getCourseByDate("EARLIEST");
  }

}
