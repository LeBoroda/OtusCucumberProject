package otus.pages.implementation;

import otus.annotations.Path;
import otus.components.popups.CookiesPopUpComponent;
import otus.di.GuiceScoped;
import otus.exceptions.PathSupportException;
import javax.inject.Inject;

public class AbsBasePage<T> extends AbsWebPageObject {

  private static final String BASE_URL = System.getProperty("base.url", "https://otus.ru");

  @Inject
  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public String adjustUrl() {
    return BASE_URL.endsWith("/") ? BASE_URL : BASE_URL + "/";
  }

  public String getPath() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getAnnotation(Path.class);
      return path.value();
    }
    throw new PathSupportException(clazz.getName());
  }

  public T open() {
    driver.get(adjustUrl() + getPath());
    return (T) this;
  }

  protected void closeCookiesPopUpComponent(){
    CookiesPopUpComponent popUpComponent = new CookiesPopUpComponent(new GuiceScoped());
    popUpComponent.closeCookiesPopup();
  }
}
