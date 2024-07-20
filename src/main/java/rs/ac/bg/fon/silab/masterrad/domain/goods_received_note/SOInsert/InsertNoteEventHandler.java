package rs.ac.bg.fon.silab.masterrad.domain.goods_received_note.SOInsert;

import io.jkratz.mediator.core.EventHandler;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.dto.GoodsReceivedNoteDTO;

@Component
@Log
public class InsertNoteEventHandler
        implements EventHandler<GoodsReceivedNoteDTO> {
    @Override
    public void handle(@NotNull GoodsReceivedNoteDTO goodsReceivedNoteDTO) {
        log.info("Goods received note inserted! " + goodsReceivedNoteDTO);
    }
}
