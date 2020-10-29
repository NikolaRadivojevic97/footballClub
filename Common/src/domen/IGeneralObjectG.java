/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Windows HD
 */
public interface IGeneralObjectG extends Serializable {

    public String getTableNameG();

    public int getAttNum();

    public void setGeneralObject(IGeneralObjectG obj);

    public String getCriteriaRowName();

    public String getCriteriaRowName2();

    public String getCriteriaValue();

    public String getCriteriaValue2();

    public String getIdRowName();

    public String getId();

}
