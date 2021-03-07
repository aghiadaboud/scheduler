package de.hhu.terminplaner.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Test {
  @Id
  Long id;

  String s;

  Test2 test2;

  Set<Test3> test3 = new HashSet<>();


  public Test(String s) {
    this.s = s;
  }


  public void addTest3(Test3 newt) {
    this.test3.add(newt);
  }
}
