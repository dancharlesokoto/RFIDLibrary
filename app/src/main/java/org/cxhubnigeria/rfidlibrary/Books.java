package org.cxhubnigeria.rfidlibrary;

public class Books {

    public String id;

    public String bookName;
    public String bookInfo;
    public String isdn;
    public String timestamp;
    public String position;
    public String status;
    public String rfidTag;




    public Books(){

    }

    public Books(String id, String bookName,String isdn,String rfidTag, String position, String status) {
        this.id = id;
        this.bookName = bookName;
        //this.bookInfo = bookInfo;
        this.isdn = isdn;
        this.rfidTag = rfidTag;
        this.position = position;
        this.status = status;
        //this.timestamp = timestamp;
    }


    public String getId(){
        return id;
    }

    public String getBookName() {
        return bookName;
    }


    public String getBookInfo() {
        return bookInfo;
    }


    public String getIsdn() {
        return isdn;
    }

    public String getRfidTag() {
        return rfidTag;
    }


    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }


    public String getTimestamp() {
        return timestamp;
    }



}
