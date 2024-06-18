package gallery_dinosaur.controller;



import gallery_dinosaur.DTO.DinossauroRequestDTO;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<DinossauroResponseDTO> getById(@PathVariable Long id) {
        DinossauroResponseDTO dinossauro = repository.findById(id)
                .map(DinossauroResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Dinossauro não encontrado neste ID: " + id));
        return ResponseEntity.ok(dinossauro);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarDinossauro(@PathVariable Long id, @javax.validation.Valid @RequestBody DinossauroRequestDTO data) {
        try {
            Optional<Dinossauro> dinossauroOptional = repository.findById(id);
            if (dinossauroOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dieta não encontrada para o ID: " + id);
            }
            Dinossauro dinossauro = dinossauroOptional.get();
            dinossauro.setNome(data.nome());
            repository.save(dinossauro);
            return ResponseEntity.status(HttpStatus.OK).body("Dinossauro do ID: " + id + " atualizado com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o dinossauro com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Dinpssauro.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarDinossauro(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Dinossauro> dinossauroOptional = repository.findById(id);
            if (dinossauroOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dinossauro não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Dinossauro do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            LOGGER.info("Erro ao deletar a dinossauro." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o dinossauro. Por favor, tente novamente mais tarde.");
        }
    }
}

