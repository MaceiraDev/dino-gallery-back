package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DinossauroRequestDTO;
import gallery_dinosaur.DTO.DinossauroResponseDTO;
import gallery_dinosaur.model.*;
import gallery_dinosaur.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("dinossauro")
public class DinossauroController {
    private static final Logger LOGGER = Logger.getLogger(DinossauroController.class.getName());

    @Autowired
    DinossauroRepository repository;

    @Autowired
    CladoRepository cladoRepository;

    @Autowired
    DietaRepository dietaRepository;

    @Autowired
    DominioRepository dominioRepository;

    @Autowired
    EspecieRepository especieRepository;

    @Autowired
    FamiliaRepository familiaRepository;

    @Autowired
    FiloRepository filoRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    MetodoLocomocaoRepository metodoLocomocaoRepository;

    @Autowired
    PeriodoRepository periodoRepository;

    @Autowired
    ReinoRepository reinoRepository;

    @Autowired
    SubFamiliaRepository subFamiliaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DinossauroResponseDTO> getAll() {
        List<DinossauroResponseDTO> dinoList = repository.findAll().stream().map(DinossauroResponseDTO::new).toList();
        return dinoList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<DinossauroResponseDTO> getById(@PathVariable Long id) {
        DinossauroResponseDTO dino = repository.findById(id)
                .map(DinossauroResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Dinossauro não encontrado neste ID: " + id));
        return ResponseEntity.ok(dino);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<GlobalExceptionHandler.MessageResponse> salvarDinossauro(@Valid @RequestBody DinossauroRequestDTO data) {
        Clado clado = cladoRepository.findById(data.getCladoId()).orElseThrow(() -> new EntityNotFoundException("Clado não encontrado"));
        Dieta dieta = dietaRepository.findById(data.getDietaId()).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada"));
        Dominio dominio = dominioRepository.findById(data.getDominioId()).orElseThrow(() -> new EntityNotFoundException("Dominio não encontrado"));
        Especie especie = especieRepository.findById(data.getEspecieId()).orElseThrow(() -> new EntityNotFoundException("Especie não encontrada"));
        Familia familia = familiaRepository.findById(data.getFamiliaId()).orElseThrow(() -> new EntityNotFoundException("Familia não encontrada"));
        Filo filo = filoRepository.findById(data.getFiloId()).orElseThrow(() -> new EntityNotFoundException("Filo não encontrado"));
        Genero genero = generoRepository.findById(data.getGeneroId()).orElseThrow(() -> new EntityNotFoundException("Genero não encontrado"));
        MetodoLocomocao metodoLocomocao = metodoLocomocaoRepository.findById(data.getMetodoLocomocaoId()).orElseThrow(() -> new EntityNotFoundException("Metodo de Locomocao não encontrado"));
        Periodo periodo = periodoRepository.findById(data.getPeriodoId()).orElseThrow(() -> new EntityNotFoundException("Periodo não encontrado"));
        Reino reino = reinoRepository.findById(data.getReinoId()).orElseThrow(() -> new EntityNotFoundException("Reino não encontrado"));
        SubFamilia subFamilia = subFamiliaRepository.findById(data.getSubFamiliaId()).orElseThrow(() -> new EntityNotFoundException("SubFamilia não encontrada"));

        Dinossauro dinossauroData = new Dinossauro(data, clado, dieta, dominio, especie, familia, filo, genero, metodoLocomocao, periodo, reino, subFamilia);
        repository.save(dinossauroData);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GlobalExceptionHandler.MessageResponse("Dinossauro criado com sucesso!"));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<GlobalExceptionHandler.MessageResponse> deletarDinossauro(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GlobalExceptionHandler.MessageResponse("ID não pode ser nulo."));
        }
        Optional<Dinossauro> dinossauroOptional = repository.findById(id);
        if (dinossauroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GlobalExceptionHandler.MessageResponse("Dinossauro não encontrado para o ID: " + id));
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalExceptionHandler.MessageResponse("Dinossauro do ID: " + id + " deletado com sucesso!"));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GlobalExceptionHandler.MessageResponse> atualizarDinossauro(@PathVariable Long id, @Valid @RequestBody DinossauroRequestDTO data) {
        Optional<Dinossauro> dinossauroOptional = repository.findById(id);
        if (dinossauroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GlobalExceptionHandler.MessageResponse("Dinossauro não encontrado para o ID: " + id));
        }
        Dinossauro dinossauro = dinossauroOptional.get();
        dinossauro.setNome(data.getNome());
        dinossauro.setTamanho(data.getTamanho());
        dinossauro.setPeso(data.getPeso());
        dinossauro.setDietaPrincipal(data.getDietaPrincipal());
        dinossauro.setHabitatNatural(data.getHabitatNatural());
        dinossauro.setClado(cladoRepository.findById(data.getCladoId()).orElseThrow(() -> new EntityNotFoundException("Clado não encontrado")));
        dinossauro.setDieta(dietaRepository.findById(data.getDietaId()).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada")));
        dinossauro.setDominio(dominioRepository.findById(data.getDominioId()).orElseThrow(() -> new EntityNotFoundException("Dominio não encontrado")));
        dinossauro.setEspecie(especieRepository.findById(data.getEspecieId()).orElseThrow(() -> new EntityNotFoundException("Especie não encontrada")));
        dinossauro.setFamilia(familiaRepository.findById(data.getFamiliaId()).orElseThrow(() -> new EntityNotFoundException("Familia não encontrada")));
        dinossauro.setFilo(filoRepository.findById(data.getFiloId()).orElseThrow(() -> new EntityNotFoundException("Filo não encontrado")));
        dinossauro.setGenero(generoRepository.findById(data.getGeneroId()).orElseThrow(() -> new EntityNotFoundException("Genero não encontrado")));
        dinossauro.setMetodoLocomocao(metodoLocomocaoRepository.findById(data.getMetodoLocomocaoId()).orElseThrow(() -> new EntityNotFoundException("Metodo de Locomocao não encontrado")));
        dinossauro.setPeriodo(periodoRepository.findById(data.getPeriodoId()).orElseThrow(() -> new EntityNotFoundException("Periodo não encontrado")));
        dinossauro.setReino(reinoRepository.findById(data.getReinoId()).orElseThrow(() -> new EntityNotFoundException("Reino não encontrado")));
        dinossauro.setSubFamilia(subFamiliaRepository.findById(data.getSubFamiliaId()).orElseThrow(() -> new EntityNotFoundException("SubFamilia não encontrada")));
        repository.save(dinossauro);
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalExceptionHandler.MessageResponse("Dinossauro do ID: " + id + " atualizada com sucesso!"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalExceptionHandler.MessageResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        LOGGER.severe("Entity not found: " + ex.getMessage());
        return new ResponseEntity<>(new GlobalExceptionHandler.MessageResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionHandler.MessageResponse> handleGlobalException(Exception ex) {
        LOGGER.severe("Error occurred: " + ex.getMessage());
        return new ResponseEntity<>(new GlobalExceptionHandler.MessageResponse("Ouve algum erro, reclame com o caetano. https://www.facebook.com/photo/?fbid=602140598682206&set=a.261967682699501&locale=pt_BR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static class GlobalExceptionHandler {
        static class MessageResponse {
            private String message;

            public MessageResponse(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}
