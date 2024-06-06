package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.DietaRequestDTO;
import gallery_dinosaur.DTO.DietaResponseDTO;
import gallery_dinosaur.model.Dieta;
import gallery_dinosaur.repository.DietaRepository;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("dieta")
public class DietaController {
    private static final Logger LOGGER = Logger.getLogger(DietaController.class.getName());

    @Autowired
    DietaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DietaResponseDTO> geAll() {
        List<DietaResponseDTO> dietaList = repository.findAll().stream().map(DietaResponseDTO::new).toList();
        return dietaList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<DietaResponseDTO> getById(@PathVariable Long id) {
        DietaResponseDTO dieta = repository.findById(id)
                .map(DietaResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Clado não encontrado neste ID: " + id));
        return ResponseEntity.ok(dieta);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDieta(@Valid @RequestBody DietaRequestDTO data) {
        Dieta dietaData = new Dieta(data);
        repository.save(dietaData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dieta criada com sucesso!");

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarDieta(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Dieta> dietaOptional = repository.findById(id);
            if (dietaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dieta não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Dieta do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            LOGGER.info("Erro ao deletar a Dieta." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a Dieta. Por favor, tente novamente mais tarde.");
        }
    }

}
