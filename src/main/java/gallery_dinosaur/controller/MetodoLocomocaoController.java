package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import gallery_dinosaur.DTO.MetodoLocomocaoResponseDTO;
import gallery_dinosaur.model.MetodoLocomocao;

import gallery_dinosaur.repository.MetodoLocomocaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Controller
@RequestMapping("metodo-locomocao")
public class MetodoLocomocaoController {
    @Autowired
    MetodoLocomocaoRespository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<MetodoLocomocaoResponseDTO> geAll() {
        List<MetodoLocomocaoResponseDTO> metodolocomocaoList = repository.findAll().stream().map(MetodoLocomocaoResponseDTO::new).toList();
        return metodolocomocaoList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarMetodoLocomocao(@Valid @RequestBody MetodoLocomocaoRequestDTO data) {
        MetodoLocomocao metodolocomocaoData = new MetodoLocomocao(data);
        repository.save(metodolocomocaoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Metodo de Locomoção criada com sucesso!");

    }
}
