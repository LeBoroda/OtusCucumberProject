package otus.components;

import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.components.implementation.AbsComponent;
import otus.di.GuiceScoped;
import java.util.*;

public class OnlineCourseTileComponent extends AbsComponent<OnlineCourseTileComponent> {
  @Inject
  public OnlineCourseTileComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private final String onlineCourseTilesSelector = ".lessons a";
  private final String onlineCourseTileSelectorTemplate = ":nth-of-type(%d)";
  private final String onlineCourseTitleSelectorTemplate = " div[class*='lessons__new-item-title']";
  private final String onlineCoursePriceSelectorTemplate = " div[class*='lessons__new-item-price']";

  public void getCourseByPrice(String priceRange) {
    List<WebElement> onlineCourseTiles = driver.findElements(By.cssSelector(onlineCourseTilesSelector));
    List<Integer> onlineCoursePrice = new ArrayList<>();
    Map<String, Integer> onlineCourseInfo = new HashMap<>();

    String priceSelector = String.format("%s%s%s",
        onlineCourseTilesSelector,
        onlineCourseTileSelectorTemplate,
        onlineCoursePriceSelectorTemplate
    );
    String titleSelector = String.format("%s%s%s",
        onlineCourseTilesSelector,
        onlineCourseTileSelectorTemplate,
        onlineCourseTitleSelectorTemplate
    );
    for (int i = 1; i <= onlineCourseTiles.size(); i++) {
      Integer price = Integer.parseInt(
          driver.findElement(By.cssSelector(String.format(priceSelector, i)))
              .getText()
              .trim()
              .split(" ")[0]
              .trim()
      );

      String title =
          driver.findElement(By.cssSelector(String.format(titleSelector, i)))
              .getText()
              .trim();

      onlineCourseInfo.put(title, price);
      onlineCoursePrice.add(price);
    }
    Collections.sort(onlineCoursePrice);
    if (priceRange.equals("expensive")) {
      Map.Entry<String, Integer> result = onlineCourseInfo.entrySet().stream()
          .filter(x -> x.getValue().equals(onlineCoursePrice.get(onlineCoursePrice.size() - 1)))
          .findAny().get();
      Assertions.assertEquals((int) result.getValue(), onlineCoursePrice.get(onlineCoursePrice.size() - 1));
      System.out.printf("Самый дорогой курс %s с ценой %d ₽%n", result.getKey(), result.getValue());
    } else if (priceRange.equals("cheap")) {
      Map.Entry<String, Integer> result = onlineCourseInfo.entrySet().stream()
          .filter(x -> x.getValue().equals(onlineCoursePrice.get(0)))
          .findAny().get();
      Assertions.assertEquals((int) result.getValue(), onlineCoursePrice.get(0));
      System.out.printf("Самый дешевый курс %s с ценой %d ₽%n", result.getKey(), result.getValue());
    }
  }
}
