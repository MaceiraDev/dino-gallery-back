package gallery_dinosaur.model;

import gallery_dinosaur.DTO.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "users")
@Entity(name = "User")
@EqualsAndHashCode(of = "id")
public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String name;
       private String password;
       private String email;
       private String tipo;

       public User(UserRequestDTO data) {
              this.email = data.email();
              this.password= data.password();
              this.name = data.name();
              this.tipo = data.tipo();

       }

       public void setTipo(String tipo) {
       }
}
