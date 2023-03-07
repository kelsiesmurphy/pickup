package org.pickup.backend.server.models.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.pickup.backend.server.models.Litter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "total_count",
        "count_by_type"
})
public class LitterSubmitStats {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("count_by_type")
    private Map<String, Integer> countByType;

    public LitterSubmitStats(List<Litter> litter) {
        this.totalCount = litter.size();
        this.countByType = new HashMap<>();
        for (Litter litterItem : litter) {
            this.countByType.putIfAbsent(
                    litterItem.getLitterType().getLitterTypeDesc(),
                    0
            );
            this.countByType.put(
                    litterItem.getLitterType().getLitterTypeDesc(),
                    1 + this.countByType.get(litterItem.getLitterType().getLitterTypeDesc())
            );
        }
    }
}
