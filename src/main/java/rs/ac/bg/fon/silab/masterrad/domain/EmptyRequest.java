package rs.ac.bg.fon.silab.masterrad.domain;

import io.jkratz.mediator.core.Request;

public  record EmptyRequest<RESPONSE>
        () implements Request<RESPONSE>
{ }
