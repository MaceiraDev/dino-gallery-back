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
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@Controller
@RequestMapping("especie")

public class EspecieController {
    private static final Logger LOGGER = Logger.getLogger(EspecieController.class.getName());

    @Autowired
    EspecieRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<EspecieResponseDTO> getAll() {
        List<EspecieResponseDTO> especieList = repository.findAll().stream().map(EspecieResponseDTO::new).toList();
        return especieList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarEspecie(@Valid @RequestBody EspecieRequestDTO data) {
        Especie especieData = new Especie(data);
        repository.save(especieData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Especie criada com sucesso!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEspecie(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Especie> especieOptional = repository.findById(id);
            if (especieOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especie não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Especie do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            LOGGER.info("Erro ao deletar a especie." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a especie. Por favor, tente novamente mais tarde.");
        }
    }
}
