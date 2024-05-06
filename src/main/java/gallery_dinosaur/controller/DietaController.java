package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.DietaRequestDTO;
import gallery_dinosaur.DTO.DietaResponseDTO;
import gallery_dinosaur.model.Dieta;
import gallery_dinosaur.repository.DietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("dieta")
public class DietaController {
    @Autowired
    DietaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DietaResponseDTO> geAll() {
        List<DietaResponseDTO> dietaList = repository.findAll().stream().map(DietaResponseDTO::new).toList();
        return dietaList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDieta(@Valid @RequestBody DietaRequestDTO data) {
        Dieta dietaData = new Dieta(data);
        repository.save(dietaData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dieta criada com sucesso!");

    }
}
