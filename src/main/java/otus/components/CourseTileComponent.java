package otus.components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import otus.components.implementation.AbsComponent;
import otus.data.CourseTitleData;
import otus.data.MonthNameData;
import otus.di.GuiceScoped;
import otus.exceptions.SortingParameterException;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class CourseTileComponent extends AbsComponent<CourseTileComponent> {

  private final String courseTileLocator = "(//a[contains(@href,'https://otus.ru/lessons/')])/ancestor::div[1]//a[not(contains(@href,'#'))]";
  private final String courseNameLocator = String.format("%s//div//h5", courseTileLocator);
  private final String courseDateLocator = String.format("(%s//span[contains(text(),'С') or contains(text(), 'В ')])", courseTileLocator) + "[%d]";

  //List of all course start dates from the page
  private List<LocalDate> courseDates = new ArrayList<>();

  @Inject
  public CourseTileComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public List<WebElement> getCourseTiles() {
    return driver.findElements(By.xpath(courseTileLocator));
  }

  public String getCourseName(int index) {
    String locator = String.format("(%s)[%d]", courseNameLocator, index);
    return driver.findElement(By.xpath(locator)).getText();
  }

  private String getMonthNumber(String monthName) {
    Stream<MonthNameData> calDat = Arrays.stream(MonthNameData.values());
    return calDat.filter(month -> month.getName().equals(monthName)).findFirst().get().getNumber();
  }

  //Parse and receive correct date of course start
  public LocalDate getCourseDate(int index) {
    LocalDate result = null;
    //Get string line to parse from tile
    String[] eventStringDate = driver.findElement(By.xpath(String.format(courseDateLocator, index)))
        .getText().split(" ");
    String yearNumber;
    //If the course start date indicated directly
    if (eventStringDate[0].equals("С")) {
      //Get string value of course start day
      String dayNumber = eventStringDate[1];
      if (Integer.parseInt(dayNumber) < 10)
        dayNumber = String.format("%s%s", "0", dayNumber);

      //Get string value of course start year depending on phrase used
      if (eventStringDate.length > 3 && eventStringDate[3].equals("2023")) {
        yearNumber = "2023";
      } else {
        yearNumber = "2024";
      }

      //Collect string value of date and convert to LocalDate
      String stringDate = String.format("%s-%s-%s", yearNumber, getMonthNumber(eventStringDate[2]), dayNumber);
      result = LocalDate.parse(stringDate);
    }

    /*
     ** If the course start date indicated as a period.
     ** Start day is set as 15th day of a month
     */
    if (eventStringDate[0].equals("В")) {
      if (eventStringDate[2].equals("2023")) {
        yearNumber = "2023";
      } else {
        yearNumber = "2024";
      }
      String stringDate = String.format("%s-%s-15", yearNumber, getMonthNumber(eventStringDate[1]));
      result = LocalDate.parse(stringDate);
    }
    return result;
  }

  /*
   ** In case there are several course titles requested in feature file,
   ** chose 1 title and convert into CourseTitleData element
   */
  public CourseTitleData choseCourseTitle(String courseTitleString) {
    String[] courseTitles = courseTitleString.split(",");
    String chosenCourseTitleString = courseTitles[new Random().nextInt(courseTitles.length)].trim();
    return CourseTitleData.fromString(chosenCourseTitleString);
  }

  public void getCourseByTitle(String courseTitle) {
    //List of all course tiles on the page
    List<WebElement> courses = getCourseTiles();

    //Lap of WebElement course tiles and course tiles respective course names
    Map<WebElement, String> coursesWithTitles = new HashMap<>();
    for (int i = 0; i < courses.size(); i++) {
      coursesWithTitles.put(courses.get(i), getCourseName(i + 1));
    }

    //Choose the map entry that matches given course name
    Map.Entry<WebElement, String>
        chosenNameTile = coursesWithTitles.entrySet()
        .stream()
        .filter(x -> x.getValue().equals(courseTitle))
        .findAny().get();

    Assertions.assertEquals(chosenNameTile.getValue(), courseTitle);

    actions.moveToElement(chosenNameTile.getKey()).build().perform();
    chosenNameTile.getKey().click();
  }

  public void getCourseByDate(String choiceCondition) {
    // Map of all course tiles and respective course start dates
    Map<WebElement, LocalDate> coursesWithDates = getCourseTilesWithDates();

    //Chose earliest or latest course tile and compare date on it with the earliest/latest date form the list
    WebElement result = null;
    if (choiceCondition.toUpperCase(Locale.ROOT).equals("EARLIEST")) {
      Map.Entry<WebElement, LocalDate>
          chosenTileInfo = coursesWithDates.entrySet()
          .stream()
          .reduce((x, y) -> (x.getValue().isAfter(y.getValue()) ? y : x))
          .get();
      Assertions.assertEquals(courseDates.get(0), chosenTileInfo.getValue());
      result = chosenTileInfo.getKey();
    } else if (choiceCondition.toUpperCase(Locale.ROOT).equals("LATEST")) {
      Map.Entry<WebElement, LocalDate> chosenTileInfo = coursesWithDates.entrySet().stream()
          .reduce((x, y) -> (x.getValue().isAfter(y.getValue()) ? x : y)).get();
      Assertions.assertEquals(courseDates.get(courseDates.size() - 1), chosenTileInfo.getValue());
      result = chosenTileInfo.getKey();
    } else {
      throw new SortingParameterException(choiceCondition);
    }
    actions.moveToElement(result).build().perform();
    result.click();
  }

  /*
   * Get Map of course Tiles and their start dates
   * Also get sorted list of course start dates
   */
  private Map<WebElement, LocalDate> getCourseTilesWithDates() {
    //Make sure list of course start dates is empty
    courseDates.clear();
    //List of all courses on page
    List<WebElement> courses = getCourseTiles();
    //Map of WebElement course tiles and LocalDate course tiles respective course start dates
    Map<WebElement, LocalDate> coursesWithDates = new HashMap<>();
    // Fill Map and List
    for (int i = 0; i < courses.size(); i++) {
      LocalDate courseDate = getCourseDate(i + 1);
      coursesWithDates.put(courses.get(i), courseDate);
      courseDates.add(courseDate);
    }
    Collections.sort(courseDates);
    return coursesWithDates;
  }

  public void getCourseNameAndStartDateAfterDate(String desiredDate) {
    String[] desiredDateInfo = desiredDate.split(" ");
    LocalDate desiredDateLT = LocalDate.parse(
        String.format("2024-%s-%s", getMonthNumber(desiredDateInfo[1]), desiredDateInfo[0])
    );
    List<WebElement> courseTiles = getCourseTiles();
    for (int i = 1; i <= courseTiles.size(); i++) {
      if(!getCourseDate(i).isBefore(desiredDateLT)) {
        System.out.printf("Курс '%s' стартует %s%n", getCourseName(i), getCourseDate(i));
      }
    }
  }
}
