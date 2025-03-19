package cs.dit.domain;

import lombok.Data;
import java.util.List;

@Data
public class MenuVO {
    private int id;
    private String name;
    private String category;
    private String videoUrl;
    private List<String> times; // ✅ 여러 시간대를 저장할 리스트 추가
}
