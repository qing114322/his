package com.qhit.netstorage.pojo;


/**
* Created by GeneratorCode on 2018/12/21
*/

public class Netstorage {

    private Integer fileid;    //文件id
    private String fileaname;    //文件名称
    private Integer filesize;    //文件大小
    private String uploaddate;    //上传日期
    private Integer uid;    //用户id

    public Integer getFileid() { 
        return fileid;
    }
 
    public void setFileid(Integer fileid) { 
        this.fileid = fileid;
    }
 
    public String getFileaname() { 
        return fileaname;
    }
 
    public void setFileaname(String fileaname) { 
        this.fileaname = fileaname;
    }
 
    public Integer getFilesize() { 
        return filesize;
    }
 
    public void setFilesize(Integer filesize) { 
        this.filesize = filesize;
    }
 
    public String getUploaddate() { 
        return uploaddate;
    }
 
    public void setUploaddate(String uploaddate) { 
        this.uploaddate = uploaddate;
    }
 
    public Integer getUid() { 
        return uid;
    }
 
    public void setUid(Integer uid) { 
        this.uid = uid;
    }
 

 }