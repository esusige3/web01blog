package kr.hs.dgsw.web01blog.Protocol;

public enum ResponseType {
    FAIL                (0,"Fail to run"),


    USER_GET            (101,"user list is here"),
    USER_ADD            (102,"user added"),
    USER_UPDATE         (103,"user updated"),
    USER_DELETE         (104,"user id:[%d] deleted"),
    USER_ACCESSS        (105,"user access"),

    POST_GET            (201,"post geterd"),
    POST_ADD            (202,"post added"),
    POST_UPDATE         (203,"post updated"),
    POST_DELETE         (204,"post deleted"),


    ATTACHMENT_STORED   (301,"attachment stored");

    final private int code;
    final private String desc;

    ResponseType(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int code(){return this.code; }

    public String desc(){return this.desc;}

}
