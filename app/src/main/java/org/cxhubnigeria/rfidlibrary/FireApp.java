package org.cxhubnigeria.rfidlibrary;

public class FireApp {

    public String id;

    public String bookName;
    public String bookInfo;
    public String isdn;
    public String timestamp;
    public String position;
    public String status;




    public FireApp(){

    }

    public FireApp(String id, String bookName,String isdn,String timestamp, String status) {
        this.id = id;
        this.bookName = bookName;
        //this.bookInfo = bookInfo;
        this.isdn = isdn;
        //this.rfidTag = rfidTag;
        //this.position = position;
        this.status = status;
        this.timestamp = timestamp;
    }


    public String getId(){
        return id;
    }




}
