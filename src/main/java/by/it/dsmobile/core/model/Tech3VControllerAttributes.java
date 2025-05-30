package by.it.dsmobile.core.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Tech3VControllerAttributes extends ControllerAttributes {

    private int passCodeSize;
    private List<Tech3VDevice> tech3VDevices;

    public Tech3VControllerAttributes() {
        super();
    }
}
