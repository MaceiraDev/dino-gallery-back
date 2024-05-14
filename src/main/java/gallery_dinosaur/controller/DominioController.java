package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DominioRequestDTO;
import gallery_dinosaur.DTO.DominioResponseDTO;
import gallery_dinosaur.model.Dominio;
import gallery_dinosaur.repository.DominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("dominio")
public class DominioController {
    @Autowired
    DominioRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DominioResponseDTO> geAll() {
        List<DominioResponseDTO> dominioList = repository.findAll().stream().map(DominioResponseDTO::new).toList();
        return dominioList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDominio(@Valid @RequestBody DominioRequestDTO data) {
        Dominio dominioData = new Dominio(data);
        repository.save(dominioData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dominio criada com sucesso!");

    }

}
