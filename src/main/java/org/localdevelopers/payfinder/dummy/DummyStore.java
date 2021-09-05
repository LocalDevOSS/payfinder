package org.localdevelopers.payfinder.dummy;

import lombok.Data;

import java.util.Date;

@Data
public class DummyStore {
    private String name;                // 상호명
    private String type;                // 업종
    private String lotNumberAddress;    // 지번주소
    private String roadNameAddress;     // 도로명주소
    private Date createAt;              // 데이터 생성 일자
    private Date updateAt;              // 데이터 수정 일자
    private String zipCode;             // 우편번호
    private String latitude;            // 위도
    private String longitude;           // 경도
    private String siGunCode;           // 시군코드
    private String siGunName;           // 시군명
}