package rs.ac.bg.fon.silab.diplomskirad.domain;

import io.jkratz.mediator.core.Request;

public  record EmptyRequest<RESPONSE>
        () implements Request<RESPONSE>
{ }
