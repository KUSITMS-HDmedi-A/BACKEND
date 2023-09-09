package HDmedi.Server.domain.tag;

import lombok.Getter;

@Getter
public enum TagType {
    E, W; // E : 효능 태그, W: 경고 태그
    int value;
}
