package com.lhj.model.other;

import com.lhj.common.model.Pages;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * lombok
 * @author 17822
 */
@Data //作用于类上，是以下注解的集合：@ToString @EqualsAndHashCode(覆盖默认的equals和hashCode) @Getter @Setter @RequiredArgsConstructo
@ToString
@NoArgsConstructor ///无参构造器
@Accessors(chain = true) //生成无参构造器
public class Test extends Pages<Test> {

    private String name;
    private int age;

    public static void main(String[] args) {
        Test t = new Test();
        t.getName();
        t.setAge(12).setName("klajf");
    }

}

