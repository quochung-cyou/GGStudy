package web.common.shared;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BaseMetadata {
    private String createBy;
    private String modifyBy;
    private Timestamp createTime;
    private Timestamp modifyTime;
}
