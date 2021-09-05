package org.localdevelopers.payfinder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class Filter {
    private String payType;
    private String storeType;
}
