/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSB;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 *
 * @author user
 */
public interface SaveToXML extends EJBObject{
    public void bookSave() throws RemoteException;
}
