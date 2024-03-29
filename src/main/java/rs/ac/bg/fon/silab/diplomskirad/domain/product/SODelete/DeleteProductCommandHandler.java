package rs.ac.bg.fon.silab.diplomskirad.domain.product.SODelete;

import io.jkratz.mediator.core.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DeleteCommand;
import rs.ac.bg.fon.silab.diplomskirad.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class DeleteProductCommandHandler
        implements CommandHandler<DeleteCommand> {

    private final ProductRepository repository;

    @Override
    public void handle(@NotNull DeleteCommand deleteRequest) {
        final Long idForDelete = deleteRequest.id();
        repository.deleteById(idForDelete);
    }
}
