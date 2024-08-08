package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import gallery_dinosaur.DTO.MetodoLocomocaoResponseDTO;
import gallery_dinosaur.model.MetodoLocomocao;
import gallery_dinosaur.repository.MetodoLocomocaoRepository;
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
@RequestMapping("metodo-locomocao")
public class MetodoLocomocaoController {
    private static final Logger LOGGER = Logger.getLogger(MetodoLocomocaoController.class.getName());

    @Autowired
    MetodoLocomocaoRepository repository;

    @GetMapping
    public List<MetodoLocomocaoResponseDTO> geAll() {
        List<MetodoLocomocaoResponseDTO> metodolocomocaoList = repository.findAll().stream().map(MetodoLocomocaoResponseDTO::new).toList();
        return metodolocomocaoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoLocomocaoResponseDTO> getById(@PathVariable Long id) {
        MetodoLocomocaoResponseDTO metodoLocomocao = repository.findById(id)
                .map(MetodoLocomocaoResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Metodo de locomoção não encontrada neste ID: " + id));
        return ResponseEntity.ok(metodoLocomocao);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarMetodoLocomocao(@Valid @RequestBody MetodoLocomocaoRequestDTO data) {
        MetodoLocomocao metodolocomocaoData = new MetodoLocomocao(data);
        repository.save(metodolocomocaoData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Metodo de Locomoção criada com sucesso!");

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMetodoLocomocao(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<MetodoLocomocao> metodoLocomocaoOptional = repository.findById(id);
            if (metodoLocomocaoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metodo de locomoção não encontrada para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Metodo de Locomoção do ID: " + id + " deletada com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar" + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Metodo de Locomoção. Por favor, tente novamente mais tarde.");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarMetodoLocomocao(@PathVariable Long id, @Valid @RequestBody MetodoLocomocaoRequestDTO data) {
        try {
            Optional<MetodoLocomocao> metodolocomocaoOptional = repository.findById(id);
            if (metodolocomocaoOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Metodo Locomocao não encontrada para o ID: " + id);
            }
            MetodoLocomocao metodolocomocao = metodolocomocaoOptional.get();
            metodolocomocao.setTipo(data.tipo());
            repository.save(metodolocomocao);
            return ResponseEntity.status(HttpStatus.OK).body("Metodo Locomocao do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Metodo Locomocao com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Metodo Locomocao. Por favor, tente novamente mais tarde.");
        }
    }

}
