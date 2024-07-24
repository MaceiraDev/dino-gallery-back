package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.DinossauroRequestDTO;
import gallery_dinosaur.DTO.DinossauroResponseDTO;
import gallery_dinosaur.model.*;
import gallery_dinosaur.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ImageRepository imageRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DinossauroResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(DinossauroResponseDTO::new)
                .toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<DinossauroResponseDTO> getById(@PathVariable Long id) {
        Optional<Dinossauro> dinossauroOptional = repository.findById(id);
        if (dinossauroOptional.isPresent()) {
            DinossauroResponseDTO dino = new DinossauroResponseDTO(dinossauroOptional.get());
            return ResponseEntity.ok(dino);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/salvar")
    public ResponseEntity<DinossauroResponseDTO> salvarDinossauro(@Valid @RequestBody DinossauroRequestDTO data) {
        Clado clado = cladoRepository.findById(data.getCladoId()).orElse(null);
        Dieta dieta = dietaRepository.findById(data.getDietaId()).orElse(null);
        Dominio dominio = dominioRepository.findById(data.getDominioId()).orElse(null);
        Especie especie = especieRepository.findById(data.getEspecieId()).orElse(null);
        Familia familia = familiaRepository.findById(data.getFamiliaId()).orElse(null);
        Filo filo = filoRepository.findById(data.getFiloId()).orElse(null);
        Genero genero = generoRepository.findById(data.getGeneroId()).orElse(null);
        MetodoLocomocao metodoLocomocao = metodoLocomocaoRepository.findById(data.getMetodoLocomocaoId()).orElse(null);
        Periodo periodo = periodoRepository.findById(data.getPeriodoId()).orElse(null);
        Reino reino = reinoRepository.findById(data.getReinoId()).orElse(null);
        SubFamilia subFamilia = subFamiliaRepository.findById(data.getSubFamiliaId()).orElse(null);

        if (clado == null || dieta == null || dominio == null || especie == null || familia == null ||
                filo == null || genero == null || metodoLocomocao == null || periodo == null || reino == null || subFamilia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Dinossauro dinossauroData = new Dinossauro(data, clado, dieta, dominio, especie, familia, filo, genero, metodoLocomocao, periodo, reino, subFamilia);
        Dinossauro savedDinossauro = repository.save(dinossauroData);

        DinossauroResponseDTO responseDTO = new DinossauroResponseDTO(savedDinossauro);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<String> deletarDinossauro(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
        }

        Optional<Dinossauro> dinossauroOptional = repository.findById(id);
        if (dinossauroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dinossauro não encontrado para o ID: " + id);
        }

        List<Image> images = imageRepository.findByDinossauroId(id);
        if (!images.isEmpty()) {
            System.out.println("Imagens associadas encontradas: " + images);
        } else {
            System.out.println("Nenhuma imagem associada encontrada.");
        }

        imageRepository.deleteByDinossauroId(id);
        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Dinossauro do ID: " + id + " deletado com sucesso!");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarDinossauro(@PathVariable Long id, @Valid @RequestBody DinossauroRequestDTO data) {
        Optional<Dinossauro> dinossauroOptional = repository.findById(id);
        if (dinossauroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dinossauro não encontrado para o ID: " + id);
        }

        Dinossauro dinossauro = dinossauroOptional.get();
        dinossauro.setNome(data.getNome());
        dinossauro.setTamanho(data.getTamanho());
        dinossauro.setPeso(data.getPeso());
        dinossauro.setDietaPrincipal(data.getDietaPrincipal());
        dinossauro.setHabitatNatural(data.getHabitatNatural());
        dinossauro.setClado(cladoRepository.findById(data.getCladoId()).orElse(null));
        dinossauro.setDieta(dietaRepository.findById(data.getDietaId()).orElse(null));
        dinossauro.setDominio(dominioRepository.findById(data.getDominioId()).orElse(null));
        dinossauro.setEspecie(especieRepository.findById(data.getEspecieId()).orElse(null));
        dinossauro.setFamilia(familiaRepository.findById(data.getFamiliaId()).orElse(null));
        dinossauro.setFilo(filoRepository.findById(data.getFiloId()).orElse(null));
        dinossauro.setGenero(generoRepository.findById(data.getGeneroId()).orElse(null));
        dinossauro.setMetodoLocomocao(metodoLocomocaoRepository.findById(data.getMetodoLocomocaoId()).orElse(null));
        dinossauro.setPeriodo(periodoRepository.findById(data.getPeriodoId()).orElse(null));
        dinossauro.setReino(reinoRepository.findById(data.getReinoId()).orElse(null));
        dinossauro.setSubFamilia(subFamiliaRepository.findById(data.getSubFamiliaId()).orElse(null));

        repository.save(dinossauro);
        return ResponseEntity.status(HttpStatus.OK).body("Dinossauro do ID: " + id + " atualizado com sucesso!");
    }
}
