package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.PeriodoRequestDTO;
import gallery_dinosaur.DTO.PeriodoResponseDTO;
import gallery_dinosaur.model.Periodo;
import gallery_dinosaur.repository.PeriodoRepository;
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

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarPeriodo(@Valid @RequestBody PeriodoRequestDTO data) {
        Periodo periodoData = new Periodo(data);
        repository.save(periodoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Periodo criada com sucesso!");

    }
    @DeleteMapping("/deletar{id}")
    public ResponseEntity<String> deletarPeriodo(@PathVariable Long id){
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
        } catch (Exception e){
            LOGGER.info("Erro ao deletar" + id);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Metodo de Locomoção. Por favor, tente novamente mais tarde.");
        }
    }
}
