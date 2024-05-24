package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.FamiliaRequestDTO;
import gallery_dinosaur.DTO.FamiliaResponseDTO;
import gallery_dinosaur.model.Familia;
import gallery_dinosaur.repository.FamiliaRepository;
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
@RequestMapping("familia")
public class FamiliaController {
    private static final Logger LOGGER = Logger.getLogger(FamiliaController.class.getName());

    @Autowired
    FamiliaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FamiliaResponseDTO> geAll() {
        List<FamiliaResponseDTO> familiaList = repository.findAll().stream().map(FamiliaResponseDTO::new).toList();
        return familiaList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarFamilia(@Valid @RequestBody FamiliaRequestDTO data) {
        Familia familiaData = new Familia(data);
        repository.save(familiaData);
        return ResponseEntity.status(HttpStatus.CREATED).body(" Familia criado com sucesso!");
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFamilia(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Familia> familiaOptional = repository.findById(id);
            if (familiaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Familia não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Familia do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            LOGGER.info("Erro ao deletar a Familia." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a Familia. Por favor, tente novamente mais tarde.");
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarFamilia(@PathVariable Long id, @Valid @RequestBody FamiliaRequestDTO data) {
        try {
            Optional<Familia> familiaOptional = repository.findById(id);
            if (familiaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Familia não encontrada para o ID: " + id);
            }
            Familia familia = familiaOptional.get();
            familia.setTipo(data.tipo());
            repository.save(familia);
            return ResponseEntity.status(HttpStatus.OK).body("Familia do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar a Familia com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a Familia. Por favor, tente novamente mais tarde.");
        }
    }
}