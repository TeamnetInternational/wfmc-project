package org.wfmc.elo.utils;

import de.elo.ix.client.*;
import org.wfmc.impl.utils.FileUtils;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Lucian.Dragomir on 3/4/2015.
 */
public class EloUtils {
    public static final int FOLDER_ROOT_ID_ELO = 1;
    public final static FileUtils fileUtilsElo = new FileUtils("ARCPATH:", String.valueOf((char) 182));
    public final static FileUtils fileUtilsRegular = new FileUtils("/", "/");

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        return currentDate;
    }

    public static Calendar convertIso2Calendar(IXConnection ixConnection, String isoDate) {
        if (isoDate == null || isoDate.equals("")) {
            return null;
        }
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(ixConnection.isoToDate(isoDate));
            return calendar;
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertCalendar2Iso(IXConnection ixConnection, GregorianCalendar date) {
        return ixConnection.dateToIso(date.getTime());
    }

    public static Sord createSord(IXConnection ixConnection, String parentPathNameOrId, String maskId, String name) throws de.elo.utils.net.RemoteException {
        Sord sord = null;
        sord = ixConnection.ix().createSord(parentPathNameOrId, maskId, SordC.mbAll);
        return sord;
    }

    public static Sord getSord(IXConnection ixConnection, String pathNameOrId, SordZ sordZ, LockZ lockZ) throws de.elo.utils.net.RemoteException {
        Sord sord = null;
        if (!isInteger(pathNameOrId)) {
            pathNameOrId = fileUtilsRegular.convertPathName(pathNameOrId, fileUtilsElo);
        }
        try {
            sord = ixConnection.ix().checkoutSord(pathNameOrId, sordZ, lockZ);
        } catch (RemoteException e) {
            IXError ixError = IXError.parseException((de.elo.utils.net.RemoteException) e);
            if (ixError.code != 5023) //cale incorecta
            {
                throw e;
            } else {
                // return null because the sord does not exists
            }
        }
        return sord;
    }

    public static void updateSordAttributes(Sord sord, Map<String, Object> attributes) {
        ObjKey[] objKeys = sord.getObjKeys();
        for (int i = 0; i < objKeys.length; i++) {
            if (attributes.containsKey(objKeys[i].getName())) {
                if (isArray(attributes.get(objKeys[i].getName()))) {
                    List<String> objKeyValue = new ArrayList<>();
                    for (Object value : ((Array[]) attributes.get(objKeys[i].getName()))) {
                        objKeyValue.add(String.valueOf(value));
                    }
                    objKeys[i].setData((String[]) objKeyValue.toArray(new String[objKeyValue.size()]));
                } else {
                    objKeys[i].setData(new String[]{attributes.get(objKeys[i].getName()).toString()});
                }
            }
        }
        sord.setObjKeys(objKeys);
    }

    public static int saveSord(IXConnection ixConnection, Sord sord, SordZ sordZ, LockZ unlockZ) throws de.elo.utils.net.RemoteException {
        return ixConnection.ix().checkinSord(sord, sordZ, unlockZ);
    }


    public static WFDiagram getWorkFlow(IXConnection ixConnection, String flowId, WFTypeZ wfTypeZ, WFDiagramZ wfDiagramZ, LockZ lockZ) throws de.elo.utils.net.RemoteException {
        WFDiagram wfDiagram = null;
        try {
            wfDiagram = ixConnection.ix().checkoutWorkFlow(flowId, wfTypeZ, wfDiagramZ, lockZ);
        } catch (RemoteException e) {
            IXError ixError = IXError.parseException((de.elo.utils.net.RemoteException) e);
            if (ixError.code != 5023) //cale incorecta
            {
                throw e;
            } else {
                // return null because the workflow does not exists
            }
        }
        return wfDiagram;
    }

    public static String startWorkFlow(IXConnection ixConnection, String templateId, String name, String sordId) throws de.elo.utils.net.RemoteException {
        return String.valueOf(ixConnection.ix().startWorkFlow(templateId, name, sordId));
    }


}