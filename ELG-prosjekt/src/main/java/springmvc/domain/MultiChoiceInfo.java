/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domain;

/**
 *
 * @author borgarlie
 */
public class MultiChoiceInfo {
    private String name;
    private String info;

    public MultiChoiceInfo(String name, String info) {
        this.name = name;
        this.info = info;
    }
    
    public MultiChoiceInfo() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
