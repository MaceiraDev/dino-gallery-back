package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DominioRequestDTO;
import gallery_dinosaur.DTO.DominioResponseDTO;
import gallery_dinosaur.model.Dieta;
import gallery_dinosaur.model.Dominio;
import gallery_dinosaur.repository.DominioRepository;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DominioResponseDTO> geAll() {
        List<DominioResponseDTO> dominioList = repository.findAll().stream().map(DominioResponseDTO::new).toList();
        return dominioList;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDominio(@Valid @RequestBody DominioRequestDTO data) {
        Dominio dominioData = new Dominio(data);
        repository.save(dominioData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dominio criado com sucesso!");

    }

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
