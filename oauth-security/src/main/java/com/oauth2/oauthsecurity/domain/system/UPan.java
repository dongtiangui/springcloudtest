package com.oauth2.oauthsecurity.domain.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UPan {

    public UPan(){}

    public UPan(Long UID, String UPanName, String UPanVersion, String last_up_time, String UPanMake, boolean isGrany) {
        this.UID = UID;
        this.UPanName = UPanName;
        this.UPanVersion = UPanVersion;
        this.last_up_time = last_up_time;
        this.UPanMake = UPanMake;
        this.isGrany = isGrany;
    }

    private Long UID;

    private String UPanName;

    private String UPanVersion;

    private String last_up_time;

    private String UPanMake;

    private boolean isGrany;


}
