package gallery_dinosaur.controller;

import gallery_dinosaur.repository.MezozoicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("mezozoico")
public class MezozoicoController {

    @Autowired
    private MezozoicoRepository mezozoicoRepository;


    @GetMapping("/contarIds")
    public ResponseEntity<Integer> contarTodosIds() {
        int count = mezozoicoRepository.countAllIds();
        return ResponseEntity.ok(count);
    }
}
