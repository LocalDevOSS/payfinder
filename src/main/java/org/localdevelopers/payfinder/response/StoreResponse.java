package org.localdevelopers.payfinder.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {

    private Long userId;

    private String address;

    private String workTime;

    private String phoneNumber;

    private String[] menu;

    private String imgUrl;

}
