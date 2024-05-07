package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.PeriodoRequestDTO;
import gallery_dinosaur.DTO.PeriodoResponseDTO;
import gallery_dinosaur.model.Periodo;
import gallery_dinosaur.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("periodo")

public class PeriodoController {

    @Autowired
    PeriodoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PeriodoResponseDTO> geAll() {
        List<PeriodoResponseDTO> periodoList = repository.findAll().stream().map(PeriodoResponseDTO::new).toList();
        return periodoList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarPeriodo(@Valid @RequestBody PeriodoRequestDTO data) {
        Periodo periodoData = new Periodo(data);
        repository.save(periodoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Periodo criada com sucesso!");

    }


}
