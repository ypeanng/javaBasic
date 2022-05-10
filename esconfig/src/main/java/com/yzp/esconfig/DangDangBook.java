package com.yzp.esconfig;

import java.math.BigDecimal;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DangDangBook {
    private String name;
    private int words;
    private String content;
    private BigDecimal price;
    private String publishDate;
    private String id;

}
