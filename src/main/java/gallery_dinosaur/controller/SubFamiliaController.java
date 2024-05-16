package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.SubFamiliaRequestDTO;
import gallery_dinosaur.DTO.SubFamiliaResponseDTO;
import gallery_dinosaur.model.SubFamilia;
import gallery_dinosaur.repository.SubFamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("SubFamilia")

public class SubFamiliaController {
    @Autowired
    SubFamiliaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping

    public List<SubFamiliaResponseDTO> getAll() {
        List<SubFamiliaResponseDTO> subfamiliaList = repository.findAll().stream().map(SubFamiliaResponseDTO::new).toList();
        return subfamiliaList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarSubFamilia(@Valid @RequestBody SubFamiliaRequestDTO data) {
        SubFamilia subfamiliaData = new SubFamilia(data);
        repository.save(subfamiliaData);
        return ResponseEntity.status(HttpStatus.CREATED).body("SubFamilia criada com sucesso!");
    }
}
