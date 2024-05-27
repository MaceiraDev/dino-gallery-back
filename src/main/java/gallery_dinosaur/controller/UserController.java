package gallery_dinosaur.controller;

import gallery_dinosaur.DTO.UserRequestDTO;
import gallery_dinosaur.DTO.UserResponseDTO;
import gallery_dinosaur.model.User;
import gallery_dinosaur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@Controller
@RequestMapping("users")

public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping

    public List<UserResponseDTO> getAll() {
        List<UserResponseDTO> userList = repository.findAll().stream().map(UserResponseDTO::new).toList();
        return userList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarUser(@Valid @RequestBody UserRequestDTO data) {
        User userData = new User(data);
        repository.save(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body("User criado com sucesso!");
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUser(@PathVariable Long id){
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não pode ser nulo.");
            }
            Optional<User> userOptional = repository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User não encontrado para o ID: " + id);
            }
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User do ID: " + id + " deletado com sucesso!");
        } catch (Exception e){
            LOGGER.info("Erro ao deletar" + id);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o User. Por favor, tente novamente mais tarde.");
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO data) {
        try {
            Optional<User> userOptional = repository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User não encontrado para o ID: " + id);
            }
            User user = userOptional.get();
            user.setTipo(data.tipo());
            repository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User do ID: " + id + " atualizada com sucesso!");
        } catch (Exception e) {
            LOGGER.severe("Erro ao atualizar o User com ID: " + id + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o User. Por favor, tente novamente mais tarde.");
        }
    }

}
