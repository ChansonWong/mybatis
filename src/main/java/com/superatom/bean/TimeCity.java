package com.superatom.bean;

public class TimeCity {
  private String id;
  private String cityid;
  private String cityname;
  private String pinyinFull;
  private String pinyinShort;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCityid() {
    return cityid;
  }

  public void setCityid(String cityid) {
    this.cityid = cityid;
  }

  public String getCityname() {
    return cityname;
  }

  public void setCityname(String cityname) {
    this.cityname = cityname;
  }

  public String getPinyinFull() {
    return pinyinFull;
  }

  public void setPinyinFull(String pinyinFull) {
    this.pinyinFull = pinyinFull;
  }

  public String getPinyinShort() {
    return pinyinShort;
  }

  public void setPinyinShort(String pinyinShort) {
    this.pinyinShort = pinyinShort;
  }

  @Override
  public String toString() {
    return "TimeCity{" +
      "id='" + id + '\'' +
      ", cityid='" + cityid + '\'' +
      ", cityname='" + cityname + '\'' +
      ", pinyinFull='" + pinyinFull + '\'' +
      ", pinyinShort='" + pinyinShort + '\'' +
      '}';
  }
}
