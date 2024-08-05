package com.cc.common.enums;

public enum ApplyEnum {
  PROCESS_HEADER_APPLYING("主管审核中"),
  PROCESS_ADMIN_APPLYING("管理员审核中"),

  STATUS_HEADER_APPLYING("待主管审核"),
  STATUS_HEADER_APPLY_OK("主管审核通过"),
  STATUS_HEADER_APPLY_NO("主管审核不通过"),

  STATUS_ADMIN_APPLYING("待管理员审核"),
  STATUS_ADMIN_APPLY_OK("管理员审核通过"),
  STATUS_ADMIN_APPLY_NO("管理员审核不通过"),

  APPLY_OK("审批通过"),
  APPLY_NO("审批不通过"),
  APPLY_DONE("审批结束"),

  ;

  public String status;

  ApplyEnum(String status) {
    this.status = status;
  }
}
