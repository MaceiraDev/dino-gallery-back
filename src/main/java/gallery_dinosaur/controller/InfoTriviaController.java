package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.InfoTriviaRequestDTO;
import gallery_dinosaur.DTO.InfoTriviaResponseDTO;
import gallery_dinosaur.model.InfoTrivia;
import gallery_dinosaur.repository.InfoTriviaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Controller
@RequestMapping("info-trivia")
public class InfoTriviaController {
    private static final Logger LOGGER = Logger.getLogger(InfoTriviaController.class.getName());

    @Autowired
    InfoTriviaRepository repository;

    @GetMapping
    public List<InfoTriviaResponseDTO> getAll() {
        List<InfoTriviaResponseDTO> infoTriviaResponsList = repository.findAll().stream().map(InfoTriviaResponseDTO::new).toList();
        return infoTriviaResponsList;
    }
    @GetMapping("/todos-site")
    public List<InfoTriviaResponseDTO> getAllSite() {
        List<InfoTriviaResponseDTO> infoTriviaResponsList = repository.findAll().stream().map(InfoTriviaResponseDTO::new).toList();
        return infoTriviaResponsList;
    }
    @GetMapping("/{id}")
    public ResponseEntity<InfoTriviaResponseDTO> getById(@PathVariable Long id) {
        InfoTriviaResponseDTO infoTrivia = repository.findById(id).map(InfoTriviaResponseDTO::new).orElseThrow(()
                -> new EntityNotFoundException("Trivia não encontrada neste ID: " + id));
        return ResponseEntity.ok(infoTrivia);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarInfoTrivia(@Valid @RequestBody InfoTriviaRequestDTO data) {
        InfoTrivia infoTriviaDATA = new InfoTrivia(data);
        repository.save(infoTriviaDATA);
        return ResponseEntity.status(HttpStatus.CREATED).body("Trivia criada com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarInfoTrivia(@PathVariable Long id, @Valid @RequestBody InfoTriviaRequestDTO data) {
        InfoTrivia infoTrivia = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trivia não encontrada neste ID: " + id));
        infoTrivia.setResposta(data.resposta());
        infoTrivia.setPergunta(data.pergunta());
        repository.save(infoTrivia);
        return ResponseEntity.status(HttpStatus.OK).body("Trivia atualizada com sucesso!");
    }
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<String> deletarInfoTrivia(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Trivia deletada com sucesso!");
    }

}
