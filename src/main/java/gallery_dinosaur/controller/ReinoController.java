package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.ReinoRequestDTO;
import gallery_dinosaur.DTO.ReinoResponseDTO;
import gallery_dinosaur.model.Reino;
import gallery_dinosaur.model.SubFamilia;
import gallery_dinosaur.repository.ReinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("reino")

public class ReinoController {

    @Autowired
    ReinoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ReinoResponseDTO> geAll() {
        List<ReinoResponseDTO> reinoList = repository.findAll().stream().map(ReinoResponseDTO::new).toList();
        return reinoList;
    }
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarReino(@Valid @RequestBody ReinoRequestDTO data) {
        Reino reinoData = new Reino(data);
        repository.save(reinoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reino criada com sucesso!");
    }

}




