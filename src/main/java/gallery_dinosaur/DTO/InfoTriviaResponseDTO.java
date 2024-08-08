package gallery_dinosaur.DTO;

import gallery_dinosaur.model.InfoTrivia;

public record InfoTriviaResponseDTO(Long id, String pergunta, String resposta) {
    public InfoTriviaResponseDTO(InfoTrivia infoTrivia) {
        this(infoTrivia.getId(), infoTrivia.getPergunta(),infoTrivia.getResposta());
    }

}