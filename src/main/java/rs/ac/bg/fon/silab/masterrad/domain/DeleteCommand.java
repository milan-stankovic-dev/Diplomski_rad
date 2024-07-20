package rs.ac.bg.fon.silab.masterrad.domain;

import io.jkratz.mediator.core.Command;

public record DeleteCommand(
    Long id
) implements Command { }
