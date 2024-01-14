package otus.data;

import otus.exceptions.CourseTitleException;

public enum CourseTitleData {
  APACHEKAFKA("Apache Kafka"),
  DEVREL("DevRel");

  private final String name;

  CourseTitleData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static CourseTitleData fromString(String courseTitle){
    for(CourseTitleData courseTitleData : CourseTitleData.values()) {
      if(courseTitleData.name.equalsIgnoreCase(courseTitle)){
        return courseTitleData;
      }
    }
    throw new CourseTitleException(courseTitle);
  }
}
