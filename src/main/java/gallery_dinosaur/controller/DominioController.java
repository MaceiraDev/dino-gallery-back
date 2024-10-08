package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DominioRequestDTO;
import gallery_dinosaur.DTO.DominioResponseDTO;
import gallery_dinosaur.model.Dominio;
import gallery_dinosaur.repository.DominioRepository;
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
@RequestMapping("dominio")
public class DominioController {
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(DominioController.class.getName());
    @Autowired
    DominioRepository repository;

    @GetMapping
    public List<DominioResponseDTO> getAll() {
        List<DominioResponseDTO> dominioList = repository.findAll().stream().map(DominioResponseDTO::new).toList();
        return dominioList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DominioResponseDTO> getById(@PathVariable Long id) {
        DominioResponseDTO dominio = repository.findById(id)
                .map(DominioResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Dominio não encontrada neste ID: " + id));
        return ResponseEntity.ok(dominio);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDominio(@Valid @RequestBody DominioRequestDTO data) {
        Dominio dominioData = new Dominio(data);
        repository.save(dominioData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dominio criado com sucesso!");

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarDominio(@PathVariable Long id, @Valid @RequestBody DominioRequestDTO data) {
        try {
            Optional<Dominio> dominioOptional = repository.findById(id);
            if (dominioOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dominio não encontrada para o ID: " + id);
            }
            Dominio dominio = dominioOptional.get();
            dominio.setTipo(data.tipo());
            repository.save(dominio);
            return ResponseEntity.status(HttpStatus.OK).body("Dominio do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Dominio com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Dominio.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarDominio(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Dominio> dominioOptional = repository.findById(id);
            if (dominioOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dominio não encontrado para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Dominio do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar o Dominio." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a Dieta. Por favor, tente novamente mais tarde.");
        }
    }

}
