package com.example.common.enums;

public enum ApplyEnum {
    PROCESS_HEADER_APPLYING("主管审批中"),
    STATUS_HEADER_APPLYING("待主管审批中"),
    STATUS_HEADER_APPLY_OK("主管审批通过"),
    STATUS_HEADER_APPLY_NO("主管审批不通过"),

    PROCESS_ADMIN_APPLYING("管理员审批中"),
    STATUS_ADMIN_APPLYING("待管理员审批"),
    STATUS_ADMIN_APPLY_OK("管理员审批通过"),
    STATUS_ADMIN_APPLY_NO("管理员审批不通过"),
    APPLY_DONE("审批完成"),
    APPLY_OK("审批通过"),
    APPLY_NO("审批未通过"),

    ;

    public String status;

    ApplyEnum(String status) {
        this.status = status;
    }
}
