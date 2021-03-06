package ro.teamnet.wfmc.audit.strategy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.wfmc.audit.WMAEventCode;
import ro.teamnet.audit.strategy.MethodAuditingStrategy;
import ro.teamnet.wfmc.audit.constants.WfmcAuditedMethod;
import ro.teamnet.wfmc.audit.constants.WfmcAuditedParameter;
import ro.teamnet.wfmc.audit.domain.WMEventAuditWorkItem;
import ro.teamnet.wfmc.audit.domain.WMProcessInstanceAudit;
import ro.teamnet.wfmc.audit.domain.WMWorkItemAudit;
import ro.teamnet.wfmc.audit.service.WMAuditErrorService;
import ro.teamnet.wfmc.audit.service.WfmcAuditService;
import ro.teamnet.wfmc.audit.util.HMethods;

import javax.inject.Inject;


@Component
@Qualifier(value = WfmcAuditedMethod.COMPLETE_WORK_ITEM)
public class CompleteWorkItemAuditingStrategy extends HMethods implements MethodAuditingStrategy {

    @Inject
    private WfmcAuditService wfmcAuditService;

    @Inject
    private WMAuditErrorService auditErrorService;

    private WMWorkItemAudit wmWorkItemAudit;
    private WMEventAuditWorkItem wmEventAuditWorkItem;
    private WMProcessInstanceAudit processInstanceAudit;

    @Override
    public void auditMethodBeforeInvocation() {
        String username = getUserIdentification(auditInfo);
        processInstanceAudit = getWmProcessInstanceAudit();
        wmWorkItemAudit = wfmcAuditService.savewmWorkItemAudit(
                (String) getMethodParameter(WfmcAuditedParameter.WORK_ITEM_ID),
                processInstanceAudit);
        wmEventAuditWorkItem = wfmcAuditService.savewmEventAuditWorkItem(username, wmWorkItemAudit, WMAEventCode.COMPLETED_WORK_ITEM);

    }

    @Override
    public void auditMethodAfterInvocation(Object o) {
        //it doesn't execute because it doesn't need any update
    }

    @Override
    public void auditMethodInvocationError(Throwable throwable) {
        auditErrorService.saveErrorIntoEntityWmErrorAudit(throwable, processInstanceAudit, auditInfo.getMethod().getName());
    }
}
