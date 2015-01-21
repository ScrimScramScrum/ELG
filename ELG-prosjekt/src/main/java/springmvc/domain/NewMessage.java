/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domain;

import java.util.Date;

/**
 *
 * @author borgarlie
 */
public class NewMessage {
    private int id;
    private String message;
    private Date time;
    private String from;
    
    public NewMessage() {
        
    }

    public NewMessage(int id, String message, Date time, String from) {
            this.id = id;
            this.message = message;
            this.time = time;
            this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
            return time;
    }

    public void setTime(long time) {
            this.time = new Date(time);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
