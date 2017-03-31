/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSB;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 *
 * @author user
 */
public interface SaveToXMLHome extends EJBHome {
    SaveToXML create()throws RemoteException, CreateException;            
}
