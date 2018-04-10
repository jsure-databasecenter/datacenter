package com.jsure.datacenter.entitymodel;

public class Role {
    private Integer id;

    private String name;

    private Integer parentid;

    private String info;

    private String businesspermissionstring;

    private Long createtime;

    private Long updatetime;

    private Boolean isdelete;

    private Integer status;

    private Integer writerid;

    private Integer writerroleid;

    private Long departmentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getBusinesspermissionstring() {
        return businesspermissionstring;
    }

    public void setBusinesspermissionstring(String businesspermissionstring) {
        this.businesspermissionstring = businesspermissionstring == null ? null : businesspermissionstring.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWriterid() {
        return writerid;
    }

    public void setWriterid(Integer writerid) {
        this.writerid = writerid;
    }

    public Integer getWriterroleid() {
        return writerroleid;
    }

    public void setWriterroleid(Integer writerroleid) {
        this.writerroleid = writerroleid;
    }

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }
}