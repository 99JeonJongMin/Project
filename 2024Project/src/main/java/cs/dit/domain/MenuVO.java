package cs.dit.domain;

import lombok.Data;

@Data // Getter, Setter, toString 자동 생성
public class MenuVO {
    private int id; // 메뉴 ID (PK)
    private String name; // 메뉴 이름
    private String category; // 음식 카테고리 (한식, 중식, 일식 등)
    private String time; // 시간대 (아침, 점심, 저녁, 야식)
    private String videoUrl; // YouTube 영상 URL
}
