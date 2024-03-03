package tn.pi.realstate.dto.request;

import lombok.Builder;
import lombok.Getter;
import tn.pi.realstate.entities.Status;
import tn.pi.realstate.entities.User;


@Getter
@Builder
public class AddPropertyRequest {


    private String location;

    private String title;

    private float price;

    private Status status;

    private String description;


}
