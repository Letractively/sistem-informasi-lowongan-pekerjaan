/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WILLIAM
 */
@Entity
@Table(name = "schedule_interview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleInterview.findAll", query = "SELECT s FROM ScheduleInterview s"),
    @NamedQuery(name = "ScheduleInterview.findByIdScheduleInterview", query = "SELECT s FROM ScheduleInterview s WHERE s.idScheduleInterview = :idScheduleInterview"),
    @NamedQuery(name = "ScheduleInterview.findByInterviewTitle", query = "SELECT s FROM ScheduleInterview s WHERE s.interviewTitle = :interviewTitle"),
    @NamedQuery(name = "ScheduleInterview.findByDateInterview", query = "SELECT s FROM ScheduleInterview s WHERE s.dateInterview = :dateInterview"),
    @NamedQuery(name = "ScheduleInterview.findByNotesInterview", query = "SELECT s FROM ScheduleInterview s WHERE s.notesInterview = :notesInterview"),
    @NamedQuery(name = "ScheduleInterview.findByStatusInteriew", query = "SELECT s FROM ScheduleInterview s WHERE s.statusInteriew = :statusInteriew"),
    @NamedQuery(name = "ScheduleInterview.findByNotesStatus", query = "SELECT s FROM ScheduleInterview s WHERE s.notesStatus = :notesStatus")})
public class ScheduleInterview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_schedule_interview")
    private Integer idScheduleInterview;
    @Basic(optional = false)
    @Column(name = "interview_title")
    private String interviewTitle;
    @Basic(optional = false)
    @Lob
    @Column(name = "interviewer_name")
    private String interviewerName;
    @Basic(optional = false)
    @Column(name = "date_interview")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInterview;
    @Basic(optional = false)
    @Column(name = "notes_interview")
    private String notesInterview;
    @Basic(optional = false)
    @Column(name = "status_interiew")
    private String statusInteriew;
    @Basic(optional = false)
    @Column(name = "notes_status")
    private String notesStatus;
    @JoinColumn(name = "id_applicants", referencedColumnName = "id_applicants")
    @ManyToOne(optional = false)
    private Applicants idApplicants;

    public ScheduleInterview() {
    }

    public ScheduleInterview(Integer idScheduleInterview) {
        this.idScheduleInterview = idScheduleInterview;
    }

    public ScheduleInterview(Integer idScheduleInterview, String interviewTitle, String interviewerName, Date dateInterview, String notesInterview, String statusInteriew, String notesStatus) {
        this.idScheduleInterview = idScheduleInterview;
        this.interviewTitle = interviewTitle;
        this.interviewerName = interviewerName;
        this.dateInterview = dateInterview;
        this.notesInterview = notesInterview;
        this.statusInteriew = statusInteriew;
        this.notesStatus = notesStatus;
    }

    public Integer getIdScheduleInterview() {
        return idScheduleInterview;
    }

    public void setIdScheduleInterview(Integer idScheduleInterview) {
        this.idScheduleInterview = idScheduleInterview;
    }

    public String getInterviewTitle() {
        return interviewTitle;
    }

    public void setInterviewTitle(String interviewTitle) {
        this.interviewTitle = interviewTitle;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public Date getDateInterview() {
        return dateInterview;
    }

    public void setDateInterview(Date dateInterview) {
        this.dateInterview = dateInterview;
    }

    public String getNotesInterview() {
        return notesInterview;
    }

    public void setNotesInterview(String notesInterview) {
        this.notesInterview = notesInterview;
    }

    public String getStatusInteriew() {
        return statusInteriew;
    }

    public void setStatusInteriew(String statusInteriew) {
        this.statusInteriew = statusInteriew;
    }

    public String getNotesStatus() {
        return notesStatus;
    }

    public void setNotesStatus(String notesStatus) {
        this.notesStatus = notesStatus;
    }

    public Applicants getIdApplicants() {
        return idApplicants;
    }

    public void setIdApplicants(Applicants idApplicants) {
        this.idApplicants = idApplicants;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idScheduleInterview != null ? idScheduleInterview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduleInterview)) {
            return false;
        }
        ScheduleInterview other = (ScheduleInterview) object;
        if ((this.idScheduleInterview == null && other.idScheduleInterview != null) || (this.idScheduleInterview != null && !this.idScheduleInterview.equals(other.idScheduleInterview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitas.ScheduleInterview[ idScheduleInterview=" + idScheduleInterview + " ]";
    }
    
}
