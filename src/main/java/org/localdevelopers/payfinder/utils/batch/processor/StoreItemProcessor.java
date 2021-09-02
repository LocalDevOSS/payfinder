package org.localdevelopers.payfinder.utils.batch.processor;

import org.localdevelopers.payfinder.domain.Store;
import org.localdevelopers.payfinder.utils.batch.model.StoreItem;
import org.springframework.batch.item.ItemProcessor;

public class StoreItemProcessor implements ItemProcessor<StoreItem, Store> {

    @Override
    public Store process(StoreItem item) {
        return transform(item);
    }

    private Store transform(StoreItem item) {
        return Store.builder()
                .name(item.getName())
                .type(item.getType())
                .lotNumberAddress(item.getLotNumberAddress())
                .roadNameAddress(item.getRoadNameAddress())
                .createAt(item.getCreateAt())
                .zipCode(item.getZipCode())
                .latitude(item.getLatitude())
                .longitude(item.getLongitude())
                .siGunCode(item.getSiGunCode())
                .siGunName(item.getSiGunName())
                .build();
    }
}
