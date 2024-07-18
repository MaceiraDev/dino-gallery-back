package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DinossauroRequestDTO;
import gallery_dinosaur.DTO.DinossauroResponseDTO;
import gallery_dinosaur.DTO.DominioResponseDTO;
import gallery_dinosaur.model.*;
import gallery_dinosaur.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static gallery_dinosaur.controller.FiloController.LOGGER;

@RestController
@RequestMapping("dinossauro")
public class DinossauroController {
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
    public ResponseEntity<MessageResponse> salvarDinossauro(@Valid @RequestBody DinossauroRequestDTO data) {
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Dinossauro criado com sucesso!"));
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarDieta(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DietaController.MessageResponse("ID não pode ser nulo."));
            }
            Optional<Dinossauro> dinossauroOptional = repository.findById(id);
            if (dinossauroOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DietaController.MessageResponse("Dinossauro não encontrado para o ID: " + id));
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new DietaController.MessageResponse("Dinossauro do ID: " + id + " deletado com sucesso!"));
        } catch (Exception e) {
            LOGGER.info("Erro ao deletar a Dieta." + id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DietaController.MessageResponse("Erro ao deletar o Dinossauro. Por favor, tente novamente mais tarde."));
        }
    }

    // Classe interna para encapsular mensagens de resposta
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