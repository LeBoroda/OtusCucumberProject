package otus.pages;

import com.google.inject.Inject;
import otus.annotations.Path;
import otus.components.OnlineCourseTileComponent;
import otus.components.popups.CookiesPopUpComponent;
import otus.di.GuiceScoped;
import otus.pages.implementation.AbsBasePage;

@Path("online/")
public class OnlineCoursePage extends AbsBasePage<OnlineCoursePage> {
  @Inject
  public OnlineCoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @Inject
  private CookiesPopUpComponent cookiesPopUpComponent;

  @Inject
  private OnlineCourseTileComponent onlineCourseTileComponent;

  public void findCheapAndExpensiveCourseInfo() {
    cookiesPopUpComponent.closeCookiesPopup();
    onlineCourseTileComponent.getCourseByPrice("cheap");
    onlineCourseTileComponent.getCourseByPrice("expensive");
  }
}
