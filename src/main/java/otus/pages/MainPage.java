package otus.pages;

import com.google.inject.Inject;
import otus.annotations.Path;
import otus.components.CourseTileComponent;
import otus.data.CourseTitleData;
import otus.pages.implementation.AbsBasePage;
import otus.service.GuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  private GuiceScoped guiceScoped;
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
    this.guiceScoped = guiceScoped;
  }

  public void findCourseByTitle(CourseTitleData courseTitle) {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(guiceScoped);
    courseTile.getCourseByTitle(courseTitle.getName());
  }

  public void getLatestCourse() {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(guiceScoped);
    courseTile.getCourseByDate("LATEST");
  }

  public void getEarliestCourse() {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(guiceScoped);
    courseTile.getCourseByDate("EARLIEST");
  }

}
