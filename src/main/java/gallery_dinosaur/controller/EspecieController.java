package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.EspecieRequestDTO;
import gallery_dinosaur.DTO.EspecieResponseDTO;
import gallery_dinosaur.model.Especie;
import gallery_dinosaur.repository.EspecieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@Controller
@RequestMapping("especie")

public class EspecieController {
    private static final Logger LOGGER = Logger.getLogger(EspecieController.class.getName());

    @Autowired
    EspecieRepository repository;

    @GetMapping
    public List<EspecieResponseDTO> getAll() {
        List<EspecieResponseDTO> especieList = repository.findAll().stream().map(EspecieResponseDTO::new).toList();
        return especieList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieResponseDTO> getById(@PathVariable Long id) {
        EspecieResponseDTO dieta = repository.findById(id)
                .map(EspecieResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Especie não encontrada neste ID: " + id));
        return ResponseEntity.ok(dieta);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarEspecie(@Valid @RequestBody EspecieRequestDTO data) {
        Especie especieData = new Especie(data);
        repository.save(especieData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Especie criada com sucesso!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarEspecie(@PathVariable Long id) {
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

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarEspecie(@PathVariable Long id, @Valid @RequestBody EspecieRequestDTO data) {
        try {
            Optional<Especie> especieOptional = repository.findById(id);
            if (especieOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especie não encontrada para o ID: " + id);
            }
            Especie especie = especieOptional.get();
            especie.setTipo(data.tipo());
            repository.save(especie);
            return ResponseEntity.status(HttpStatus.OK).body("Especie do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Especie com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erro ao atualizar o Especie."));
        }
    }

    static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

