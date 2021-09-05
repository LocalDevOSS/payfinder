package org.localdevelopers.payfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
public class StoreResponse {
    private ObjectId id;

    private String sgName;

    private String name;

    private String type;

    private String address;

    private String latitude;

    private String longitude;

}
