package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.ReinoRequestDTO;
import gallery_dinosaur.DTO.ReinoResponseDTO;
import gallery_dinosaur.model.Reino;
import gallery_dinosaur.repository.ReinoRepository;
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
@RequestMapping("reino")

public class ReinoController {
    private static final Logger LOGGER = Logger.getLogger(ReinoController.class.getName());

    @Autowired
    ReinoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ReinoResponseDTO> geAll() {
        List<ReinoResponseDTO> reinoList = repository.findAll().stream().map(ReinoResponseDTO::new).toList();
        return reinoList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ReinoResponseDTO> getById(@PathVariable Long id) {
        ReinoResponseDTO reino = repository.findById(id)
                .map(ReinoResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Reino não encontrado neste ID: " + id));
        return ResponseEntity.ok(reino);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarReino(@Valid @RequestBody ReinoRequestDTO data) {
        Reino reinoData = new Reino(data);
        repository.save(reinoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reino criado com sucesso!");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarReino(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Reino> reinoOptional = repository.findById(id);
            if (reinoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reino não encontrado para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reino do ID: " + id + " deletado com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar" + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Reino. Por favor, tente novamente mais tarde.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarPeriodo(@PathVariable Long id, @Valid @RequestBody ReinoRequestDTO data) {
        try {
            Optional<Reino> reinoOptional = repository.findById(id);
            if (reinoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reino não encontrado para o ID: " + id);
            }
            Reino reino = reinoOptional.get();
            reino.setTipo(data.tipo());
            repository.save(reino);
            return ResponseEntity.status(HttpStatus.OK).body("Reino do ID: " + id + " atualizado com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Reino com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Reino. Por favor, tente novamente mais tarde.");
        }
    }

}




