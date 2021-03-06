/*--

 Copyright (C) 2002 Anthony Eden.
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows
    these conditions in the documentation and/or other materials
    provided with the distribution.

 3. The names "OBE" and "Open Business Engine" must not be used to
    endorse or promote products derived from this software without prior
    written permission.  For written permission, please contact
    me@anthonyeden.com.

 4. Products derived from this software may not be called "OBE" or
    "Open Business Engine", nor may "OBE" or "Open Business Engine"
    appear in their name, without prior written permission from
    Anthony Eden (me@anthonyeden.com).

 In addition, I request (but do not require) that you include in the
 end-user documentation provided with the redistribution and/or in the
 software itself an acknowledgement equivalent to the following:
     "This product includes software developed by
      Anthony Eden (http://www.anthonyeden.com/)."

 THIS SOFTWARE IS PROVIdED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIdENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.

 For more information on OBE, please see <http://obe.sourceforge.net/>.

 */

package org.wfmc.audit;

import org.wfmc.wapi.WMActivityInstanceState;

import java.util.Date;

/**
 * Change Activity Instance State Audit Data.
 *
 * @author Antony Lodge
 */
public class WMAChangeActivityInstanceStateData extends WMAAuditBase {
    private static final long serialVersionUID = 3105870611884980114L;

    private String _activityDefinitionBusinessName;
    private String _applicationId;
    private int _newActivityState;
    private int _previousActivityState;

    protected static String valueOf(int state) {
        return state == -1 ? null :
            WMActivityInstanceState.valueOf(state).toString();
    }

    protected static int valueOf(String state) {
        return state == null ? -1 :
            WMActivityInstanceState.valueOf(state).value();
    }

    public WMAChangeActivityInstanceStateData() {
    }

    public WMAChangeActivityInstanceStateData(String processDefinitionId,
        String activityDefinitionId, String initialProcessInstanceId,
        String currentProcessInstanceId, String activityInstanceId,
        int processState, WMAEventCode eventCode, String domainId,
        String nodeId, String userId, String roleId, Date timestamp,
        String activityDefinitionBusinessName, String applicationId,
        int newActivityState, int previousActivityState) {

        super(processDefinitionId, activityDefinitionId,
            initialProcessInstanceId, currentProcessInstanceId,
            activityInstanceId, null, processState, eventCode, domainId, nodeId,
            userId, roleId, timestamp);
        _activityDefinitionBusinessName = activityDefinitionBusinessName;
        _applicationId = applicationId;
        _newActivityState = newActivityState;
        _previousActivityState = previousActivityState;
    }

    public WMAChangeActivityInstanceStateData(String processDefinitionId,
        String activityDefinitionId, String initialProcessInstanceId,
        String currentProcessInstanceId, String activityInstanceId,
        int processState, WMAEventCode eventCode, String domainId,
        String nodeId, String userId, String roleId, Date timestamp,
        byte accountCode, short extensionNumber,
        byte extensionType, short extensionLength, short extensionCodePage,
        Object extensionContent, String activityDefinitionBusinessName,
        String applicationId, int newActivityState, int previousActivityState) {

        super(processDefinitionId, activityDefinitionId,
            initialProcessInstanceId, currentProcessInstanceId,
            activityInstanceId, null, processState, eventCode, domainId, nodeId,
            userId, roleId, timestamp, accountCode,
            extensionNumber, extensionType, extensionLength, extensionCodePage,
            extensionContent);
        _activityDefinitionBusinessName = activityDefinitionBusinessName;
        _applicationId = applicationId;
        _newActivityState = newActivityState;
        _previousActivityState = previousActivityState;
    }

    /**
     * @return Business name of the Activity
     */
    public String getActivityDefinitionBusinessName() {
        return _activityDefinitionBusinessName;
    }

    /**
     * @param activityDefinitionBusinessName Business name of the Activity
     */
    public void setActivityDefinitionBusinessName(
        String activityDefinitionBusinessName) {

        _activityDefinitionBusinessName = activityDefinitionBusinessName;
    }

    /**
     * @return Id of the application associated with this activity
     */
    public String getApplicationId() {
        return _applicationId;
    }

    /**
     * @param applicationId Id of application associated with this activity
     */
    public void setApplicationId(String applicationId) {
        _applicationId = applicationId;
    }

    /**
     * @return New Activity State
     */
    public String getNewActivityState() {
        return valueOf(_newActivityState);
    }

    /**
     * @param newActivityState New Activity State
     */
    public void setNewActivityState(String newActivityState) {
        _newActivityState = valueOf(newActivityState);
    }

    /**
     * @return Previous Activity State
     */
    public String getPreviousActivityState() {
        return valueOf(_previousActivityState);
    }

    /**
     * @param previousActivityState Previous Activity State
     */
    public void setPreviousActivityState(String previousActivityState) {
        _previousActivityState = valueOf(previousActivityState);
    }

    public String toString() {
        return "WMAChangeActivityInstanceStateData[cwadPrefix=" +
            formatCwadPrefix() +
            ", activityDefinitionBusinessName='" +
            _activityDefinitionBusinessName + '\'' +
            ", applicationId=" + _applicationId +
            ", newActivityState=" + getNewActivityState() +
            ", previousActivityState=" + getPreviousActivityState() +
            ", cwadSuffix=" + formatCwadSuffix() +
            ']';
    }
}