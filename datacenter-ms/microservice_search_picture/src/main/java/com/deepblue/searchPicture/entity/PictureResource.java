package com.deepblue.searchPicture.entity;

import com.alibaba.fastjson.JSONArray;

public class PictureResource {

    String id;       // 行 id
    String package_name;  //包名
    String package_md5;
    String path;         //路径名
    String path_md5;
    String project;      //项目名称
    String mark_class;   //标注类别
    String batch_id;     //批次号
    Integer picture_number;  //图片数量
    String mark_type;        //标注形式
    String mark_stuff_class;  //标注物类别
    String mark_stuff_desc;   //标注物描述
    String sence;             //场景描述
    JSONArray demo_photo_list;   //图片样例列表
    String markStartTime;        //标注开始时间
    String updateTime;
    String createTime;
    String FormatMarkStartTime;  //标注开始时间(格式化的)
    String start_markStartTime;
    String end_markStartTime;
    int pageNum;
    int pageSize;

    public String getFormatMarkStartTime() {
        return FormatMarkStartTime;
    }

    public void setFormatMarkStartTime(String formatMarkStartTime) {
        FormatMarkStartTime = formatMarkStartTime;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public Integer getPicture_number() {
        return picture_number;
    }

    public void setPicture_number(Integer picture_number) {
        this.picture_number = picture_number;
    }

    public String getMarkStartTime() {
        return markStartTime;
    }

    public void setMarkStartTime(String markStartTime) {
        this.markStartTime = markStartTime;
        this.setFormatMarkStartTime(this.markStartTime);
    }

    public String getPackage_name() {
        if(!(package_name == null || package_name.equals("")))
            return package_name;
        else
            return null;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_md5() {
        if(!(package_md5 == null || package_md5.equals("")))
            return package_md5;
        else
            return null;
    }

    public void setPackage_md5(String package_md5) {
        this.package_md5 = package_md5;
    }

    public String getPath() {
        if(!(path == null || path.equals("")))
            return path;
        else
            return null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath_md5() {
        if(!(path_md5 == null || path_md5.equals("")))
            return path_md5;
        else
            return null;
    }

    public void setPath_md5(String path_md5) {
        this.path_md5 = path_md5;
    }

    public String getProject() {
        if(!(project == null || project.equals("")))
            return project;
        else
            return null;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMark_type() {
        if(!(mark_type == null || mark_type.equals("")))
            return mark_type;
        else
            return null;
    }

    public void setMark_type(String mark_type) {
        this.mark_type = mark_type;
    }


    public String getSence() {
        if(!(sence == null || sence.equals("")))
            return sence;
        else
            return null;
    }

    public void setSence(String sence) {
        this.sence = sence;
    }

    public JSONArray getDemo_photo_list() {
        return demo_photo_list;
    }

    public void setDemo_photo_list(JSONArray demo_photo_list) {
        this.demo_photo_list = demo_photo_list;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMark_class() {
        if(!(mark_class == null || mark_class.equals("")))
            return mark_class;
        else
            return null;
    }

    public void setMark_class(String mark_class) {
        this.mark_class = mark_class;
    }

    public String getMark_stuff_class() {
        if(!(mark_stuff_class == null || mark_stuff_class.equals("")))
            return mark_stuff_class;
        else
            return null;
    }

    public void setMark_stuff_class(String mark_stuff_class) {
        this.mark_stuff_class = mark_stuff_class;
    }

    public String getMark_stuff_desc() {
        if(!(mark_stuff_desc == null || mark_stuff_desc.equals("")))
            return mark_stuff_desc;
        else
            return null;
    }

    public void setMark_stuff_desc(String mark_stuff_desc) {
        this.mark_stuff_desc = mark_stuff_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getStart_markStartTime() {
        return start_markStartTime;
    }

    public void setStart_markStartTime(String start_markStartTime) {
        this.start_markStartTime = start_markStartTime;
    }

    public String getEnd_markStartTime() {
        return end_markStartTime;
    }

    public void setEnd_markStartTime(String end_markStartTime) {
        this.end_markStartTime = end_markStartTime;
    }

    @Override
    public String toString() {
        return "PictureResource{" +
                "id='" + id + '\'' +
                ", package_name='" + package_name + '\'' +
                ", package_md5='" + package_md5 + '\'' +
                ", path='" + path + '\'' +
                ", path_md5='" + path_md5 + '\'' +
                ", project='" + project + '\'' +
                ", mark_class='" + mark_class + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", picture_number=" + picture_number +
                ", mark_type='" + mark_type + '\'' +
                ", mark_stuff_class='" + mark_stuff_class + '\'' +
                ", mark_stuff_desc='" + mark_stuff_desc + '\'' +
                ", sence='" + sence + '\'' +
                ", demo_photo_list='" + demo_photo_list + '\'' +
                ", markStartTime=" + markStartTime +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", FormatMarkStartTime='" + FormatMarkStartTime + '\'' +
                '}';
    }
    
    public boolean isEmpty(){
        return this.mark_class==null&&this.mark_type==null
                &&this.sence==null&&this.mark_stuff_class==null&&this.mark_stuff_desc==null
                &&this.markStartTime==null&&this.project==null&&this.start_markStartTime==null&&this.end_markStartTime==null;
    }
}
