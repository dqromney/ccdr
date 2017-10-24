package com.dqr;

import lombok.Data;

/**
 * Created by dqromney on 10/23/17.
 */
@Data
public class MyDto {
    private String stringValue;
    private int intValue;
    private boolean booleanValue;

    public MyDto() {
        super();
    }
}
