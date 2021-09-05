package org.localdevelopers.payfinder.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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
 *
 * @author yw-park
 */
@Slf4j
@RequestMapping("/dummy")
@RestController
public class DummyStoreController {

    private final ObjectMapper objectMapper;

    private static final int SEARCH_SIZE = 1000;

    @Value("${dummy.external.api.prefix}")
    private String EXTERNAL_API_URL_PREFIX;

    @Value("${dummy.external.api.key}")
    private String EXTERNAL_API_KEY;

    public DummyStoreController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<DummyStore> storeList(@RequestParam(required = false) String payType, @RequestParam(required = false) String storeType) throws UnsupportedEncodingException, JsonProcessingException {

        String decodeServiceKey = URLDecoder.decode(EXTERNAL_API_KEY, "UTF-8");

        RestTemplate restTemplate = new RestTemplate();
        new HttpHeaders().setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXTERNAL_API_URL_PREFIX).queryParam("KEY", decodeServiceKey)
                .queryParam("Type", "json")
                .queryParam("pSize", SEARCH_SIZE);

        if (StringUtils.hasText(payType)) {
            builder.queryParam("SIGUN_NM", payType);
        }

        UriComponents uri = builder.build();

        String response = restTemplate.getForObject(uri.toUriString(), String.class);
        Map<String, Object> map = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {
        });
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("RegionMnyFacltStus");
        List<HashMap<String, String>> hashMapList = (List<HashMap<String, String>>) list.get(1).get("row");
        return hashMapList.stream().map(m -> {
            DummyStore dummyStore = new DummyStore();
            dummyStore.setName(m.get("CMPNM_NM"));
            dummyStore.setType(m.get("INDUTYPE_NM"));
            dummyStore.setRoadNameAddress(m.get("REFINE_ROADNM_ADDR"));
            dummyStore.setLatitude(m.get("REFINE_WGS84_LAT"));
            dummyStore.setLongitude(m.get("REFINE_WGS84_LOGT"));
            return dummyStore;
        }).collect(Collectors.toList());
    }
}
