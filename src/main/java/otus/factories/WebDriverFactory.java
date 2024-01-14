package otus.factories;

import otus.exceptions.BrowserSupportException;
import otus.factories.implementation.ChromeSettings;
import otus.factories.implementation.FireFoxSettings;
import otus.factories.implementation.IBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory {
  private final String browserName = System.getProperty("browser", "chrome");

  public EventFiringWebDriver createDriver() {
    EventFiringWebDriver driver;
    switch (browserName) {
      case "chrome": {
        IBrowserSettings chromeSettings = new ChromeSettings();
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver(chromeSettings.configureDriver()));
        return driver;
      }
      case "firefox": {
        IBrowserSettings fireFoxSettings = new FireFoxSettings();
        WebDriverManager.firefoxdriver().setup();
        driver = new EventFiringWebDriver(new FirefoxDriver(fireFoxSettings.configureDriver()));
        return driver;
      }
      case "opera": {
        WebDriverManager.operadriver().setup();
        driver = new EventFiringWebDriver(new OperaDriver());
        return driver;
      }
      default: {
        throw new BrowserSupportException(browserName);
      }
    }
  }
}
