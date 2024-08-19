package gallery_dinosaur.controller;


import gallery_dinosaur.DTO.FamiliaResponseDTO;
import gallery_dinosaur.DTO.FilmeRequestDTO;
import gallery_dinosaur.DTO.FilmeResponseDTO;
import gallery_dinosaur.model.Filme;
import gallery_dinosaur.repository.FilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@Controller
@RequestMapping("filme")
public class FilmeController {
    private static final Logger LOGGER = Logger.getLogger(FilmeController.class.getName());
    @Autowired
    FilmeRepository repository;
    private Long id;

    @GetMapping
    public List<FilmeResponseDTO> getAll() {
        List<FilmeResponseDTO> filmeList = repository.findAll().stream().map(FilmeResponseDTO::new).toList();
        return filmeList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> getById(@PathVariable Long id) {
        FilmeResponseDTO filme = repository.findById(id).map(FilmeResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado neste ID: " + id));
        return ResponseEntity.ok(filme);
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarFilme(@Valid @RequestBody FilmeRequestDTO data ) {
        Filme filmeData = new Filme(data);
        repository.save(filmeData);
        return ResponseEntity.status(HttpStatus.CREATED).body(" Filme criado com sucesso!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFilme(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<Filme> filmeOptional = repository.findById(id);
            if (filmeOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Filme do ID: " + id + " deletado com sucesso!");
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar o Filme." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o Filme.  Por favor, tente novamente mais tarde.");
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarFilme(@PathVariable Long Id, @Valid @RequestBody FilmeRequestDTO data) {
        try {
            Optional<Filme> filmeOptional = repository.findById(id);
            if (filmeOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado para o ID: " + id);
            }
            Filme filme = filmeOptional.get();
            filme.setNome(data.nome());
            repository.save(filme);
            return ResponseEntity.status(HttpStatus.OK).body("Filme do ID: " + id + " atualizado com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o Filme com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o Filme. Por favor, tente novamente mais tarde.");
        }

    }


}
