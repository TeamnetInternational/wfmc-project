package ro.teamnet.wfmc.audit.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ioan.Ivan on 3/24/2015.
 */

@Entity
@Table(name = "WM_WORK_ITEM_AUDIT")
public class WMWorkItemAudit {

    @Id
    @SequenceGenerator(name = "WM_WORK_ITEM", sequenceName = "WM_WORK_ITEM_AUDIT_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WM_WORK_ITEM")
    @Column(name = "ID")
    private Long id;

    @Column(name = "WORK_ITEM_ID")
    private String workItemId;

    @ManyToOne
    @JoinColumn(name = "WM_PROCESS_INSTANCE_AUDIT_ID")
    private WMProcessInstanceAudit wmProcessInstanceAudit;

    @Transient
    @OneToMany(mappedBy = "wmWorkItemAudit")
    private List<WMAttributeAuditWorkItem> wmAttributeAuditWorkItemList;

    @Transient
    @OneToMany(mappedBy = "wmWorkItemAudit")
    private List<WMEventAuditWorkItem> wmEventAuditWorkItemList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public WMProcessInstanceAudit getWmProcessInstanceAudit() {
        return wmProcessInstanceAudit;
    }

    public void setWmProcessInstanceAudit(WMProcessInstanceAudit wmProcessInstanceAudit) {
        this.wmProcessInstanceAudit = wmProcessInstanceAudit;
    }

    public List<WMAttributeAuditWorkItem> getWmAttributeAuditWorkItemList() {
        return wmAttributeAuditWorkItemList;
    }

    public void setWmAttributeAuditWorkItemList(List<WMAttributeAuditWorkItem> wmAttributeAuditWorkItemList) {
        this.wmAttributeAuditWorkItemList = wmAttributeAuditWorkItemList;
    }

    public List<WMEventAuditWorkItem> getWmEventAuditWorkItemList() {
        return wmEventAuditWorkItemList;
    }

    public void setWmEventAuditWorkItemList(List<WMEventAuditWorkItem> wmEventAuditWorkItemList) {
        this.wmEventAuditWorkItemList = wmEventAuditWorkItemList;
    }
}
