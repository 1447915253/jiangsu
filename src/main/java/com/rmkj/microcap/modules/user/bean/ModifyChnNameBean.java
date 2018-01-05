package com.rmkj.microcap.modules.user.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 123 on 2016/10/18.
 */
public class ModifyChnNameBean {

    @NotBlank
    @Length(max = 16)
    private String chnName;

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }
}
