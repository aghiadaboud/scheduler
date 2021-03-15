package de.hhu.propra.terminplaner.service.uebung;

import de.hhu.propra.terminplaner.domain.uebung.Uebung;
import de.hhu.propra.terminplaner.domain.zeitslot.Zeitslot;
import de.hhu.propra.terminplaner.repos.UebungRepository;
import de.hhu.propra.terminplaner.repos.ZeitslotRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UebungService {

  private UebungRepository uebungRepository;

  private ZeitslotRepository zeitslotRepository;

  public UebungService(UebungRepository uebungRepository, ZeitslotRepository zeitslotRepository) {
    this.uebungRepository = uebungRepository;
    this.zeitslotRepository = zeitslotRepository;
  }

  public List<Uebung> findAllUebungen() {
    return uebungRepository.findAll();
  }

  public Uebung findUebungById(Long uebungid) {
    Optional<Uebung> uebung = uebungRepository.findById(uebungid);

    if (uebung.isPresent()) {
      return uebung.get();
    } else {
      throw new NullPointerException("keine Uebung für diese ID vorhanden");
    }
//        return uebungRepository.findById(uebungid).orElseThrow(() ->
//                new ResponseStatusException(NOT_FOUND, "Keine Uebung mit id " + uebungid + " vorhanden."));
  }

  public Long createUebung(String name, Boolean gruppenanmeldung, LocalDate von, LocalDate bis) {
    Uebung uebung = new Uebung(name, gruppenanmeldung, von, bis);
    uebungRepository.save(uebung);
    return uebung.getId();
  }


  public List<Zeitslot> getAllZeitslotOfUebung(Uebung uebung) {
    List<Zeitslot> zeitslots = new ArrayList<>();
    zeitslots.addAll(uebung.getZeitslots());
    return zeitslots;
  }

  public List<Zeitslot> getAllFreieZeitslotOfUebung(Uebung uebung) {
    List<Zeitslot> zeitslots = getAllZeitslotOfUebung(uebung);
    List<Zeitslot> freieZeitslots =
        zeitslots.stream()
            .filter(zeitslot -> !zeitslot.getReserviert())
            .collect(Collectors.toList());
    return freieZeitslots;
  }


  public Optional<List<Zeitslot>> getAllReservierteZeitslots(Uebung uebung) {
    List<Zeitslot> zeitslots = getAllZeitslotOfUebung(uebung);
    List<Zeitslot> reservierteZeitslots =
        zeitslots.stream()
            .filter(zeitslot -> zeitslot.getReserviert())
            .collect(Collectors.toList());
    if (reservierteZeitslots.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(reservierteZeitslots);
  }


  public void deleteZeitslot(Long uebungid, @NonNull Zeitslot zeitslot)
      throws NullPointerException {
    Uebung uebung = findUebungById(uebungid);
    uebung.getZeitslots().remove(zeitslot);
    uebungRepository.save(uebung);
  }

  public Uebung saveUebung(Uebung uebung) {
    uebungRepository.save(uebung);
    return uebung;
  }

  public Uebung findUebungByZeitslotId(Long id) {
    Long uebungId = zeitslotRepository.findUebungIdByZeitslotId(id);
    return findUebungById(uebungId);
  }
}
