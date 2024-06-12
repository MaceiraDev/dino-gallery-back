package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.MetodoLocomocaoResponseDTO;
import gallery_dinosaur.DTO.PeriodoRequestDTO;
import gallery_dinosaur.DTO.PeriodoResponseDTO;
import gallery_dinosaur.model.Periodo;
import gallery_dinosaur.repository.PeriodoRepository;
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
@RequestMapping("periodo")

public class PeriodoController {
    private static final Logger LOGGER = Logger.getLogger(PeriodoController.class.getName());

    @Autowired
    PeriodoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PeriodoResponseDTO> geAll() {
        List<PeriodoResponseDTO> periodoList = repository.findAll().stream().map(PeriodoResponseDTO::new).toList();
        return periodoList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PeriodoResponseDTO> getById(@PathVariable Long id) {
        PeriodoResponseDTO periodo = repository.findById(id)
                .map(PeriodoResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Periodo não encontrada neste ID: " + id));
        return ResponseEntity.ok(periodo);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarPeriodo(@Valid @RequestBody PeriodoRequestDTO data) {
        Periodo periodoData = new Periodo(data);
        repository.save(periodoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Periodo criada com sucesso!");

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPeriodo(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Periodo> periodoOptional = repository.findById(id);
            if (periodoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Periodo não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Periodo do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar" + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Metodo de Locomoção. Por favor, tente novamente mais tarde.");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarPeriodo(@PathVariable Long id, @Valid @RequestBody PeriodoRequestDTO data) {
        try {
            Optional<Periodo> periodoOptional = repository.findById(id);
            if (periodoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Periodo não encontrada para o ID: " + id);
            }
            Periodo periodo = periodoOptional.get();
            periodo.setTipo(data.tipo());
            repository.save(periodo);
            return ResponseEntity.status(HttpStatus.OK).body("Periodo do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Periodo com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Periodo. Por favor, tente novamente mais tarde.");
        }
    }

}
