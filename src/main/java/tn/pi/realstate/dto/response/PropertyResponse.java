package tn.pi.realstate.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class PropertyResponse {
    private String location;

    private String title;

    private float price;

    private String status;

    private String description;
}
