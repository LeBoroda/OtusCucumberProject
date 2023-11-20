package otus.components.popups;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import otus.components.implementation.AbsComponent;
import otus.service.GuiceScoped;

public class CookiesPopUpComponent extends AbsComponent<CookiesPopUpComponent> implements IPopUp {
  private final String cookiesPopupLocator = "//*[contains (text(),'Посещая наш сайт, вы принимаете')]";
  private final String cookiesAcceptButtonLocator = "//*[contains (text(),'Посещая наш сайт, вы принимаете')]//following::button";

  @Inject
  public CookiesPopUpComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void closeCookiesPopup() {
    popUpShouldNotBeVisible();
    driver.findElement(By.xpath(cookiesAcceptButtonLocator)).click();
  }

  @Override
  public void popUpShouldNotBeVisible() {
    waiter.waitForElementVisibility(driver.findElement(By.xpath(cookiesPopupLocator)));
  }

  @Override
  public void popUpShouldBeVisible() {
    waiter.waitForElementNonVisibility(driver.findElement(By.xpath(cookiesPopupLocator)));
  }
}
