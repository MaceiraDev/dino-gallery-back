package gallery_dinosaur.controller;



import gallery_dinosaur.DTO.DinossauroResponseDTO;
import gallery_dinosaur.model.Dinossauro;
import gallery_dinosaur.repository.DinossauroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static gallery_dinosaur.controller.FiloController.LOGGER;

@RestController
@Controller
@RequestMapping("dinossauro")
public class DinossauroController {
    private static final Logger LOGGER = Logger.getLogger(DinossauroController.class.getName());

    @Autowired
    DinossauroRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DinossauroResponseDTO> getAll() {
        List<DinossauroResponseDTO> dinossauroList = repository.findAll().stream().map(DinossauroResponseDTO::new).toList();
        return dinossauroList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDinossauro(@Valid @RequestBody DinossauroResponseDTO data) {
        Dinossauro dinossauroData = new Dinossauro(data);
        repository.save(dinossauroData);
        return ResponseEntity.status(HttpStatus.CREATED).body("dinossauro criado com sucesso!");

    }


}

