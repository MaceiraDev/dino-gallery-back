package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.CladoRequestDTO;
import gallery_dinosaur.DTO.CladoResponseDTO;
import gallery_dinosaur.model.Clado;
import gallery_dinosaur.repository.CladoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("clado")
public class CladoController {
    private static final Logger LOGGER = Logger.getLogger(CladoController.class.getName());

    @Autowired
    CladoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CladoResponseDTO> geAll() {
        List<CladoResponseDTO> cladoList = repository.findAll().stream().map(CladoResponseDTO::new).toList();
        return cladoList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CladoResponseDTO> getById(@PathVariable Long id) {
        CladoResponseDTO clado = repository.findById(id)
                .map(CladoResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Clado não encontrado neste ID: " + id));
        return ResponseEntity.ok(clado);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarClado(@Valid @RequestBody CladoRequestDTO data) {
        Clado cladoData = new Clado(data);
        repository.save(cladoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Clado criado com sucesso!");

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarClado(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Clado> cladoOptional = repository.findById(id);
            if (cladoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clado não encontrado para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Clado do ID: " + id + " deletado com sucesso!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            LOGGER.info("Erro ao deletar o Clado." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Clado. Por favor, tente novamente mais tarde.");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarClado(@PathVariable Long id, @Valid @RequestBody CladoRequestDTO data) {
        try {
            Optional<Clado> cladoOptional = repository.findById(id);
            if (cladoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clado não encontrado para o ID: " + id);
            }
            Clado clado = cladoOptional.get();
            clado.setTipo(data.tipo());
            repository.save(clado);
            return ResponseEntity.status(HttpStatus.OK).body("Clado do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Clado com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Clado. Por favor, tente novamente mais tarde.");
        }
    }

}
