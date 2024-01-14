package otus.factories.implementation;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxSettings implements IBrowserSettings{
  @Override
  public MutableCapabilities configureDriver() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--start-maximized");
    firefoxOptions.addArguments("--homepage=about:blank");
    firefoxOptions.addArguments("--enable-extensions");
    return firefoxOptions;
  }
}
