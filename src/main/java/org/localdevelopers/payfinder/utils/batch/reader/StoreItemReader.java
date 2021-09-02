package org.localdevelopers.payfinder.utils.batch.reader;

import lombok.RequiredArgsConstructor;
import org.localdevelopers.payfinder.utils.api.StoreItemFetcher;
import org.localdevelopers.payfinder.utils.batch.model.StoreItem;
import org.springframework.batch.item.ItemReader;

import java.util.List;

@RequiredArgsConstructor
public class StoreItemReader implements ItemReader<StoreItem> {
    private final StoreItemFetcher storeItemFetcher;
    private List<StoreItem> items = null;
    private int index = 0;

    @Override
    public StoreItem read() {
        if (items == null)
            items = storeItemFetcher.fetchAll();
        return index < items.size() ? items.get(index++) : null;
    }
}
