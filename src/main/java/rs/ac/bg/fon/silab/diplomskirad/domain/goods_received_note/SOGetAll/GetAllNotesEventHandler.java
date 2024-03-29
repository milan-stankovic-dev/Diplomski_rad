package rs.ac.bg.fon.silab.diplomskirad.domain.goods_received_note.SOGetAll;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.diplomskirad.domain.DTOListResponse;
import rs.ac.bg.fon.silab.diplomskirad.dto.GoodsReceivedNoteDTO;

@Component
@Log
public class GetAllNotesEventHandler
        implements EventHandler<DTOListResponse<GoodsReceivedNoteDTO>> {
    @Override
    public void handle(@NotNull DTOListResponse<GoodsReceivedNoteDTO>
                                   goodsReceivedNoteDTODTOListResponse) {

        log.info("Fetched all goods received notes from the database.");
    }
}
