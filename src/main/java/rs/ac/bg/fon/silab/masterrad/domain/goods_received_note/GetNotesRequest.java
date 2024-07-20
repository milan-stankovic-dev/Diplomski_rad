package rs.ac.bg.fon.silab.masterrad.domain.goods_received_note;

import io.jkratz.mediator.core.Request;
import rs.ac.bg.fon.silab.masterrad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.masterrad.dto.GoodsReceivedNoteDTO;

public record GetNotesRequest
        () implements Request<DTOListResponse<GoodsReceivedNoteDTO>> { }
