package rs.ac.bg.fon.silab.diplomskirad.domain;

import io.jkratz.mediator.core.Command;

public record DeleteCommand(
    Long id
) implements Command { }
