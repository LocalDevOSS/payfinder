package teststore;

import org.localdevelopers.payfinder.dto.Filter;
import org.localdevelopers.payfinder.dto.PayTypeField;
import org.localdevelopers.payfinder.dto.StoreTypeField;
import org.localdevelopers.payfinder.service.StoreService;
import org.testng.annotations.Test;

public class TestStore {
    private final StoreService storeService;

    public TestStore(StoreService storeService) {
        this.storeService = storeService;
    }


    @Test
    public void getSearchKeyword() {
        Filter filter = Filter.builder()
                .keyword("오리")
                .build();

        storeService.getStoreFilter(filter);
        System.out.println("search test1");
    }

    @Test
    public void getSearchPayType() {
        Filter filter = Filter.builder()
                .keyword("오리")
                .payType(PayTypeField.GOYANG)
                .storeType(StoreTypeField.RESTAURANT)
                .build();

        storeService.getStoreFilter(filter);
        System.out.println("search test2");
    }

    @Test
    public void getLocationPayType() {
        Filter filter = Filter.builder()
                .keyword("오리")
                .payType(PayTypeField.GOYANG)
                .storeType(StoreTypeField.RESTAURANT)
                .latitude(37.6785207275)
                .longitude(127.4931391706)
                .build();

        storeService.getStoreFilter(filter);
        System.out.println("search test3");
    }
}
