package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DietaRequestDTO;
import gallery_dinosaur.DTO.DietaResponseDTO;
import gallery_dinosaur.model.Dieta;
import gallery_dinosaur.repository.DietaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("dieta")
public class DietaController {
    private static final Logger LOGGER = Logger.getLogger(DietaController.class.getName());

    @Autowired
    DietaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DietaResponseDTO> geAll() {
        return repository.findAll().stream().map(DietaResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<DietaResponseDTO> getById(@PathVariable Long id) {
        DietaResponseDTO dieta = repository.findById(id)
                .map(DietaResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada neste ID: " + id));
        return ResponseEntity.ok(dieta);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarDieta(@Valid @RequestBody DietaRequestDTO data) {
        Dieta dietaData = new Dieta(data);
        repository.save(dietaData);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Dieta criada com sucesso!"));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarDieta(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("ID não pode ser nulo."));
            }
            Optional<Dieta> dietaOptional = repository.findById(id);
            if (dietaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Dieta não encontrada para o ID: " + id));
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Dieta do ID: " + id + " deletada com sucesso!"));
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar a Dieta." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erro ao deletar a Dieta. Por favor, tente novamente mais tarde."));
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarDieta(@PathVariable Long id, @Valid @RequestBody DietaRequestDTO data) {
        try {
            Optional<Dieta> dietaOptional = repository.findById(id);
            if (dietaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Dieta não encontrada para o ID: " + id));
            }
            Dieta dieta = dietaOptional.get();
            dieta.setTipo(data.tipo());
            repository.save(dieta);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Dieta do ID: " + id + " atualizada com sucesso!"));
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Dieta com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erro ao atualizar o Dieta."));
        }
    }


    // Classe interna para encapsular mensagens de resposta
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
