package org.cxhubnigeria.rfidlibrary;
public class MovieQuote {
    private String bookName;
    private String bookInfo;
    private String isdn;
    private String rfidTag;
    private String position;
    private String status;


    private String timestamp;


    @JasonIgnore
    private String key;
    public MovieQuote(){
    }

    public String getKey() {    return key;   }

    public void setKey(String key) {  this.key = key;    }



    public MovieQuote(String bookName, String bookInfo, String isdn, String rfidTag,String position, String status, String timestamp) {
        this.bookName = bookName;
        this.bookInfo = bookInfo;
        this.isdn = isdn;
        this.rfidTag = rfidTag;
        this.position = position;
        this.status = status;
        this.timestamp = timestamp;


    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getRfidTag() {
        return rfidTag;
    }

    public void setRfidTag(String rfidTag) {
        this.rfidTag = rfidTag;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
