package com.devopstools.portal.controller;

import com.devopstools.portal.model.dto.InputDto;
import com.devopstools.portal.model.dto.OutputDto;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.devopstools.portal.util.Constants.*;

/**
 * Created by pubudu on 8/14/17.
 */
@RestController
@RequestMapping("/sample-controller")
@Api(tags = SAMPLE_CONTROLLER_TAGS, description = SAMPLE_CONTROLLER_DESCRIPTION)
public class SampleController {

    private static final Log LOG = LogFactory.getLog(SampleController.class);

    @Autowired
    Environment environment;

    @ApiOperation(value = "${SampleController.sampleMethod.value}", notes = "${SampleController.sampleMethod.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = STATUS_CODE_HTTP_CREATED, message = MESSAGE_HTTP_CREATED),
            @ApiResponse(code = STATUS_CODE_HTTP_BAD_REQUEST, message = MESSAGE_HTTP_BAD_REQUEST, response = OutputDto.class)}
    )
    @PostMapping(value = "/protected/sample-api/{path-variable}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OutputDto> sampleMethod(
            @ApiParam(value = "${SampleController.sampleMethod.authorizationHeader.value}", required = true) @RequestHeader(name = "X-Authorization", defaultValue = "${SampleController.sampleMethod.authorizationHeader.value}") final String authorizationHeader,
            @ApiParam(value = "${SampleController.sampleMethod.pathVariable.value}", required = true, defaultValue = "defaultValue") @PathVariable(name = "path-variable") String pathVariable,
            @ApiParam(value = "${SampleController.sampleMethod.status.value}", required = true) @RequestParam(name = "status", defaultValue = "${SampleController.sampleMethod.status.defaultValue}") String status,
            @ApiParam(name = "input_dto", value = "${SampleController.sampleMethod.inputDto.value}", required = true) @RequestBody InputDto inputDto
    ) {
        for (final String profileName : environment.getActiveProfiles()) {
            LOG.info("Currently Active Profile :" + profileName);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return new ResponseEntity<OutputDto>(new OutputDto("some value " + currentPrincipalName), HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "${SampleController.sampleMethod.value}", notes = "${SampleController.sampleMethod.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = STATUS_CODE_HTTP_CREATED, message = MESSAGE_HTTP_CREATED),
            @ApiResponse(code = STATUS_CODE_HTTP_BAD_REQUEST, message = MESSAGE_HTTP_BAD_REQUEST, response = OutputDto.class)}
    )
    @PostMapping(value = "/public/sample-api/{path-variable}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OutputDto> sampleMethod2(
            @ApiParam(value = "${SampleController.sampleMethod.authorizationHeader.value}", required = true) @RequestHeader(name = "X-Authorization", defaultValue = "${SampleController.sampleMethod.authorizationHeader.value}") final String authorizationHeader,
            @ApiParam(value = "${SampleController.sampleMethod.pathVariable.value}", required = true, defaultValue = "defaultValue") @PathVariable(name = "path-variable") String pathVariable,
            @ApiParam(value = "${SampleController.sampleMethod.status.value}", required = true) @RequestParam(name = "status", defaultValue = "${SampleController.sampleMethod.status.defaultValue}") String status,
            @ApiParam(name = "input_dto", value = "${SampleController.sampleMethod.inputDto.value}", required = true) @RequestBody InputDto inputDto
    ) {
        for (final String profileName : environment.getActiveProfiles()) {
            LOG.info("Currently Active Profile :" + profileName);
        }
        return new ResponseEntity<OutputDto>(new OutputDto("some value"), HttpStatus.BAD_REQUEST);
    }

}
