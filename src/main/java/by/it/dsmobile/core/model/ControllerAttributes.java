package by.it.dsmobile.core.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Tech3VControllerAttributes.class)
})
@RequiredArgsConstructor
public abstract class ControllerAttributes {

    private String url;
    private int port;
    private String note;

}
