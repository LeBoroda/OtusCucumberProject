package otus.pages;

import com.google.inject.Inject;
import otus.annotations.Path;
import otus.components.CourseTileComponent;
import otus.components.popups.CookiesPopUpComponent;
import otus.data.CourseTitleData;
import otus.di.GuiceScoped;
import otus.pages.implementation.AbsBasePage;

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
    courseTileComponent.getCourseByDate("EARLIEST");
  }

  public void getCourseNameAndStartDates(String startDate) {
    courseTileComponent.getCourseNameAndStartDateAfterDate(startDate);
  }

}
