package de.hhu.propra.terminplaner.domain.tutor;


import de.hhu.propra.terminplaner.stereotype.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AggregateRoot
public class Tutor {

  @Id
  private Long id;
  @NonNull
  private String githubname;
  private Long gruppeid;

  public Tutor(@NonNull String githubname) {
    this.githubname = githubname;
  }
}
