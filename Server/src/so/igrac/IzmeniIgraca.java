/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import so.AbstractGenericOperation;

/**
 *
 * @author nikol
 */
public class IzmeniIgraca extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object entity) throws Exception {
        // product preconditions and validation
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        return databaseBroker.izmeni((Igrac) entity);
    }
}