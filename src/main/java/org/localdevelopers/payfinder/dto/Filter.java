package org.localdevelopers.payfinder.dto;

import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {
    @Nullable
    private String keyword;

    @Nullable
    private PayTypeField payType;

    @Nullable
    private StoreTypeField storeType;
}
