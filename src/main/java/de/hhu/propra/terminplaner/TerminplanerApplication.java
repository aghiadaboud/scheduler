package de.hhu.propra.terminplaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TerminplanerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TerminplanerApplication.class, args);
  }

  //  @Scheduled(fixedRate = 2000)
//  public void t() {
//    System.out.println("hallo");
//  }

}
