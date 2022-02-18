/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Bane
 */
public class Currency extends BaseModel implements Serializable{
    private long id;
    private String name;
    private String shortname;
    
    public Currency(long id, String name, String shortname) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getAttributeList() {
        return "id, name, shortname";
    }

    @Override
    public String getClassName() {
        return "currency";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.name + "', '" + this.shortname;
    }

    @Override
    public String getQueryCondition() {
        return "id=" + id;
    }
}
