package com.devopstools.portal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pubudu on 8/14/17.
 */
@ApiModel(value = "input_dto")
public class InputDto {

    @ApiModelProperty(value = "${InputDto.someValue.value}", required = true, example = "some value")
    @JsonProperty(value = "some_value", defaultValue = "defaultValue")
    private String someValue;

    public InputDto() {
    }

    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }
}
