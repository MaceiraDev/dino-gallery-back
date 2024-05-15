package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.FiloRequestDTO;
import gallery_dinosaur.DTO.FiloResponseDTO;
import gallery_dinosaur.model.Filo;
import gallery_dinosaur.repository.FiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("filo")
public class FiloController {
    @Autowired
    FiloRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FiloResponseDTO> geAll() {
        List<FiloResponseDTO> filoList = repository.findAll().stream().map(FiloResponseDTO::new).toList();
        return filoList;
    }
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarFilo(@Valid @RequestBody FiloRequestDTO data) {
        Filo filoData = new Filo(data);
        repository.save(filoData);
        return ResponseEntity.status(HttpStatus.CREATED).body(" Filo criado com sucesso!");

    }

}
