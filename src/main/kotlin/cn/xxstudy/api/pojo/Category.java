package cn.xxstudy.api.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @date: 2024/7/8 18:27
 * @author: TangRen
 * @remark:
 */
@Data
public class Category {
    private int id;
    public String categoryName;
    private String categoryAlias;
    public int createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Category(String categoryName, String categoryAlias) {
        this.categoryName = categoryName;
        this.categoryAlias = categoryAlias;
    }
}
