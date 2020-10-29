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
public interface IGeneralObjectU extends Serializable {

    public String getTableName();

    public String getColumnNameForUpdate();

    public String getUpdateValue();

    public String getColumnNameForSet();

    public String getSetValue();

}
