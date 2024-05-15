package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.FamiliaRequestDTO;
import gallery_dinosaur.DTO.FamiliaResponseDTO;
import gallery_dinosaur.model.Familia;
import gallery_dinosaur.model.Filo;
import gallery_dinosaur.repository.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("familia")
public class FamiliaController {
    @Autowired
    FamiliaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FamiliaResponseDTO> geAll() {
        List<FamiliaResponseDTO> familiaList = repository.findAll().stream().map(FamiliaResponseDTO::new).toList();
        return familiaList;
    }
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarFamilia(@Valid @RequestBody FamiliaRequestDTO data) {
        Familia familiaData = new Familia(data);
        repository.save(familiaData);
        return ResponseEntity.status(HttpStatus.CREATED).body(" Familia criado com sucesso!");
    }

    }