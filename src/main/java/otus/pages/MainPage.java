package otus.pages;

import otus.annotations.Path;
import otus.components.CourseTileComponent;
import otus.data.CourseTitleData;
import otus.di.GuiceScoped;
import otus.pages.implementation.AbsBasePage;
import javax.inject.Inject;
import java.util.Random;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public CourseTitleData choseCourseTitle(String courseTitleString) {
    String[] courseTitles = courseTitleString.trim().split(",");
    String chosenCourseTitleString = courseTitles[new Random().nextInt(courseTitles.length)].trim();
    return CourseTitleData.fromString(chosenCourseTitleString);
  }
  public void findCourseByTitle(CourseTitleData courseTitle) {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(new GuiceScoped());
    courseTile.getCourseByTitle(courseTitle.getName());
  }
  public void findCourseByTitle(String courseTitle) {
    findCourseByTitle(choseCourseTitle(courseTitle));
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
