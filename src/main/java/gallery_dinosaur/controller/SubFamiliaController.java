package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.SubFamiliaRequestDTO;
import gallery_dinosaur.DTO.SubFamiliaResponseDTO;
import gallery_dinosaur.model.SubFamilia;
import gallery_dinosaur.repository.SubFamiliaRepository;
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
@RequestMapping("sub-familia")

public class SubFamiliaController {
    private static final Logger LOGGER = Logger.getLogger(SubFamiliaController.class.getName());

    @Autowired
    SubFamiliaRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<SubFamiliaResponseDTO> getAll() {
        List<SubFamiliaResponseDTO> subfamiliaList = repository.findAll().stream().map(SubFamiliaResponseDTO::new).toList();
        return subfamiliaList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<SubFamiliaResponseDTO> getById(@PathVariable Long id) {
        SubFamiliaResponseDTO subFamilia = repository.findById(id)
                .map(SubFamiliaResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Sub-Familia não encontrada neste ID: " + id));
        return ResponseEntity.ok(subFamilia);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarSubFamilia(@Valid @RequestBody SubFamiliaRequestDTO data) {
        SubFamilia subfamiliaData = new SubFamilia(data);
        repository.save(subfamiliaData);
        return ResponseEntity.status(HttpStatus.CREATED).body("SubFamilia criada com sucesso!");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarSubFamilia(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<SubFamilia> subfamiliaOptional = repository.findById(id);
            if (subfamiliaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SubFamilia não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("SubFamilia do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar" + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o SubFamilia. Por favor, tente novamente mais tarde.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarSubFamilia(@PathVariable Long id, @Valid @RequestBody SubFamiliaRequestDTO data) {
        try {
            Optional<SubFamilia> subfamiliaOptional = repository.findById(id);
            if (subfamiliaOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SubFamilia não encontrada para o ID: " + id);
            }
            SubFamilia subfamilia = subfamiliaOptional.get();
            subfamilia.setTipo(data.tipo());
            repository.save(subfamilia);
            return ResponseEntity.status(HttpStatus.OK).body("SubFamilia do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar a SubFamilia com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Reino. Por favor, tente novamente mais tarde.");
        }
    }

}
