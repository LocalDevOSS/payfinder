package org.localdevelopers.payfinder.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UI 더미 데이터 생성용 임시 컨트롤러
 * @author yw-park
 *
 */
@Slf4j
@RequestMapping("/dummy")
@RestController
public class DummyStoreController {

    private final ObjectMapper objectMapper;

    private static final int SEARCH_SIZE = 1000;
    private static final String EXTERNAL_API_URL_PREFIX = "https://openapi.gg.go.kr/RegionMnyFacltStus";
    private static final String EXTERNAL_API_KEY = "25bdfa85672648398e79dd5cdd85da8c";

    public DummyStoreController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<DummyStore> storeList() throws UnsupportedEncodingException, JsonProcessingException {

        String decodeServiceKey = URLDecoder.decode(EXTERNAL_API_KEY, "UTF-8");

        RestTemplate restTemplate = new RestTemplate();
        new HttpHeaders().setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(EXTERNAL_API_URL_PREFIX).queryParam("KEY", decodeServiceKey)
                .queryParam("Type", "json")
                .queryParam("pSize", SEARCH_SIZE)
                .build(false); // 자동 Encoding 막기

        String response = restTemplate.getForObject(uri.toUriString(), String.class);
        Map<String, Object> map = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {
        });
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("RegionMnyFacltStus");
        List<HashMap<String, String>> hashMapList = (List<HashMap<String, String>>) list.get(1).get("row");
        return hashMapList.stream().map(m -> {
            DummyStore dummyStore = new DummyStore();
            dummyStore.setName(m.get("CMPNM_NM"));
            dummyStore.setType(m.get("INDUTYPE_NM"));
            dummyStore.setAddress(m.get("REFINE_ROADNM_ADDR"));
            dummyStore.setLat(m.get("REFINE_WGS84_LAT"));
            dummyStore.setLogt(m.get("REFINE_WGS84_LOGT"));
            return dummyStore;
        }).collect(Collectors.toList());
    }
}
