package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.EspecieRequestDTO;
import gallery_dinosaur.DTO.EspecieResponseDTO;
import gallery_dinosaur.model.Especie;
import gallery_dinosaur.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("especie")
public class EspecieController {
    @Autowired
    EspecieRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<EspecieResponseDTO> getAll() {
      List<EspecieResponseDTO> especieList = repository.findAll().stream().map(EspecieResponseDTO::new).toList();
       return especieList;
    }
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarEspecie(@Valid @RequestBody EspecieRequestDTO data){
        Especie especieData = new Especie(data);
        repository.save(especieData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Especie criada com sucesso!");
    }


}
