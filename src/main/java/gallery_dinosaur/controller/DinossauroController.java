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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    MetodoLocomocaoRespository metodoLocomocaoRepository;

    @Autowired
    PeriodoRepository periodoRepository;

    @Autowired
    ReinoRepository reinoRepository;

    @Autowired
    SubFamiliaRepository subFamiliaRepository;

    @Autowired
    UserRepository userRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DinossauroResponseDTO> getAll() {
        List<DinossauroResponseDTO> dinossauroList = repository.findAll().stream().map(DinossauroResponseDTO::new).toList();
        return dinossauroList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<String> salvarDinossauro(@Valid @RequestBody DinossauroRequestDTO data) {
        try {
            Clado clado = cladoRepository.findById(data.clado()).orElseThrow(() -> new EntityNotFoundException("Clado not found"));
            Dieta dieta = dietaRepository.findById(data.dieta()).orElseThrow(() -> new EntityNotFoundException("Dieta not found"));
            Dominio dominio = dominioRepository.findById(data.dominio()).orElseThrow(() -> new EntityNotFoundException("Dominio not found"));
            Especie especie = especieRepository.findById(data.especie()).orElseThrow(() -> new EntityNotFoundException("Especie not found"));
            Familia familia = familiaRepository.findById(data.familia()).orElseThrow(() -> new EntityNotFoundException("Familia not found"));
            Filo filo = filoRepository.findById(data.filo()).orElseThrow(() -> new EntityNotFoundException("Filo not found"));
            Genero genero = generoRepository.findById(data.genero()).orElseThrow(() -> new EntityNotFoundException("Genero not found"));
            MetodoLocomocao metodoLocomocao = metodoLocomocaoRepository.findById(data.metodoLocomocao()).orElseThrow(() -> new EntityNotFoundException("MetodoLocomocao not found"));
            Periodo periodo = periodoRepository.findById(data.periodo()).orElseThrow(() -> new EntityNotFoundException("Periodo not found"));
            Reino reino = reinoRepository.findById(data.reino()).orElseThrow(() -> new EntityNotFoundException("Reino not found"));
            SubFamilia subFamilia = subFamiliaRepository.findById(data.subFamilia()).orElseThrow(() -> new EntityNotFoundException("SubFamilia not found"));
            User user = userRepository.findById(data.user()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            
            Dinossauro dinossauroData = new Dinossauro(data, clado, dieta, dominio, especie, familia, filo, genero, metodoLocomocao, periodo, reino, subFamilia, user);
            repository.save(dinossauroData);

            return ResponseEntity.status(HttpStatus.CREATED).body("dinossauro criado com sucesso!");
        } catch (EntityNotFoundException e) {
            LOGGER.severe("Erro ao salvar dinossauro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Erro inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao criar dinossauro");
        }
    }
}
