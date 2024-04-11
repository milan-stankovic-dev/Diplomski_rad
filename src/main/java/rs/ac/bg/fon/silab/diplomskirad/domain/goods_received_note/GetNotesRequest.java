package rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;

public record GetNotesRequest
        () implements Request<DTOListResponse<GoodsReceivedNoteDTO>> { }
