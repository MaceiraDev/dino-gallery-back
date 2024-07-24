package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.ImageRequestDTO;
import gallery_dinosaur.model.Dinossauro;
import gallery_dinosaur.model.Image;
import gallery_dinosaur.repository.DinossauroRepository;
import gallery_dinosaur.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DinossauroRepository dinossauroRepository;

    // Definindo o diretório de upload(onde será salvo as imagens)
    private static final String UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "uploads").toString();

    // Verificar e criar o diretório de upload, se necessário
    static {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/save")
    public ResponseEntity<List<ImageRequestDTO>> save(@RequestParam("file") MultipartFile file, @RequestParam("dinossauroId") Long dinossauroId) {
        try {
            Dinossauro dinossauro = dinossauroRepository.findById(dinossauroId)
                    .orElseThrow(() -> new EntityNotFoundException("Dinossauro não encontrado para o ID: " + dinossauroId));

            // Salvar o arquivo localmente
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, file.getBytes());

            // Salvar a URL da imagem no banco de dados
            Image image = new Image();
            image.setUrl(filePath.toString());
            image.setDinossauro(dinossauro);
            image = imageRepository.save(image);

            ImageRequestDTO dto = new ImageRequestDTO();
            dto.setUrl(image.getUrl());
            dto.setDinossauroId(image.getDinossauro().getId());

            return new ResponseEntity<>(List.of(dto), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
