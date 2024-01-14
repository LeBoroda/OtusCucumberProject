package otus.exceptions;

public class CourseTitleException extends RuntimeException {
  public CourseTitleException(String courseTitle) {
    super(String.format("Course title %s is not found. Please check check your input", courseTitle));
  }
}
