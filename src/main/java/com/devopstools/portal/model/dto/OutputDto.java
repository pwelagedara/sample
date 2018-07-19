package com.devopstools.portal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pubudu on 8/14/17.
 */
@ApiModel(value = "output_dto")
public class OutputDto {

    @ApiModelProperty(value = "${OutputDto.someValue.value}", required = true, example = "some value")
    @JsonProperty("some_value")
    private String someValue;

    public OutputDto() {
    }

    public OutputDto(String someValue) {
        this.someValue = someValue;
    }

    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }
}
