package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.FiloResponseDTO;
import gallery_dinosaur.DTO.GeneroRequestDTO;
import gallery_dinosaur.DTO.GeneroResponseDTO;
import gallery_dinosaur.model.Genero;
import gallery_dinosaur.repository.GeneroRepository;
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
@RequestMapping("genero")
public class GeneroController {
    private static final Logger LOGGER = Logger.getLogger(GeneroController.class.getName());

    @Autowired
    GeneroRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<GeneroResponseDTO> geAll() {
        List<GeneroResponseDTO> generoList = repository.findAll().stream().map(GeneroResponseDTO::new).toList();
        return generoList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<GeneroResponseDTO> getById(@PathVariable Long id) {
        GeneroResponseDTO genero = repository.findById(id)
                .map(GeneroResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Genero não encontrada neste ID: " + id));
        return ResponseEntity.ok(genero);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarGenero(@Valid @RequestBody GeneroRequestDTO data) {
        Genero generoData = new Genero(data);
        repository.save(generoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dieta criada com sucesso!");

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarGenero(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Genero> generoOptional = repository.findById(id);
            if (generoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genero não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Genero do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            LOGGER.info("Erro ao deletar o Genero." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a Genero. Por favor, tente novamente mais tarde.");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarGenero(@PathVariable Long id, @Valid @RequestBody GeneroRequestDTO data) {
        try {
            Optional<Genero> generoOptional = repository.findById(id);
            if (generoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dieta não encontrada para o ID: " + id);
            }
            Genero genero = generoOptional.get();
            genero.setTipo(data.tipo());
            repository.save(genero);
            return ResponseEntity.status(HttpStatus.OK).body("Dieta do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar a Genero com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Genero. Por favor, tente novamente mais tarde.");
        }
    }

}
