package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.FiloRequestDTO;
import gallery_dinosaur.DTO.FiloResponseDTO;
import gallery_dinosaur.model.Filo;
import gallery_dinosaur.repository.FiloRepository;
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
@RequestMapping("filo")
public class FiloController {
    public static final Logger LOGGER = Logger.getLogger(FiloController.class.getName());
    @Autowired
    FiloRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FiloResponseDTO> geAll() {
        List<FiloResponseDTO> filoList = repository.findAll().stream().map(FiloResponseDTO::new).toList();
        return filoList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarFilo(@Valid @RequestBody FiloRequestDTO data) {
        Filo filoData = new Filo(data);
        repository.save(filoData);
        return ResponseEntity.status(HttpStatus.CREATED).body(" Filo criado com sucesso!");

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFilo(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Filo> filoOptional = repository.findById(id);
            if (filoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filo não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Filo do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar a Filo." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Filo. Por favor, tente novamente mais tarde.");
        }
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarFilo(@PathVariable Long id, @Valid @RequestBody FiloRequestDTO data) {
        try {
            Optional<Filo> filoOptional = repository.findById(id);
            if (filoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filo não encontrada para o ID: " + id);
            }
            Filo filo = filoOptional.get();
            filo.setTipo(data.tipo());
            repository.save(filo);
            return ResponseEntity.status(HttpStatus.OK).body("Filo do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Filo com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Filo. Por favor, tente novamente mais tarde.");
        }
    }
}
