package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.ImageRequestDTO;
import gallery_dinosaur.model.Dinossauro;
import gallery_dinosaur.model.Image;
import gallery_dinosaur.repository.DinossauroRepository;
import gallery_dinosaur.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DinossauroRepository dinossauroRepository;

    @PostMapping("/save")
    public List<ImageRequestDTO> save(@RequestBody List<ImageRequestDTO> data) {
        List<Image> images = data.stream().map(dto -> {
            Dinossauro dinossauro = dinossauroRepository.findById(dto.getDinossauroId())
                    .orElseThrow(() -> new EntityNotFoundException("Dinossauro não encontrado para o ID: " + dto.getDinossauroId()));
            Image image = new Image();
            image.setUrl(dto.getUrl());
            image.setDinossauro(dinossauro);
            return image;
        }).collect(Collectors.toList());

        images = imageRepository.saveAll(images);

        return images.stream().map(image -> {
            ImageRequestDTO dto = new ImageRequestDTO();
            dto.setUrl(image.getUrl());
            dto.setDinossauroId(image.getDinossauro().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
