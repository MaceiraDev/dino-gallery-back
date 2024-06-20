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
import java.util.logging.Logger;

@RestController
@RequestMapping("dinossauro")
public class DinossauroController {
    @Autowired
    DinossauroRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DinossauroResponseDTO> getAll() {
        List<DinossauroResponseDTO> dinoList = repository.findAll().stream().map(DinossauroResponseDTO::new).toList();
        return dinoList;
    }
}