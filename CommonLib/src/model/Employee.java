/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bane
 */
public class Employee extends BaseModel implements Serializable{
    private Long id;
    private String imePrezime;
    private String JMBG;
    private String email;
    private Account account;

    public Employee() {
    }
    
    public Employee(Long id, String imePrezime, String JMBG, String email) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.JMBG = JMBG;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

        
    
  @Override
    public String getAttributeList() {
        return "id, ImePrezime, JMBG, email";
    }

    @Override
    public String getClassName() {
        return "employee";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.imePrezime + "', '" + this.JMBG + "', '" + this.email;
    }

    @Override
    public String getQueryCondition() {
        return "id=" + id;
    }

    @Override
    public BaseModel getNewRecord(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
