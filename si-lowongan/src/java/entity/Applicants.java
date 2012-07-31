/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WILLIAM
 */
@Entity
@Table(name = "applicants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applicants.findAll", query = "SELECT a FROM Applicants a"),
    @NamedQuery(name = "Applicants.findByIdApplicants", query = "SELECT a FROM Applicants a WHERE a.idApplicants = :idApplicants"),
    @NamedQuery(name = "Applicants.findByFirstName", query = "SELECT a FROM Applicants a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "Applicants.findByMiddleName", query = "SELECT a FROM Applicants a WHERE a.middleName = :middleName"),
    @NamedQuery(name = "Applicants.findByLastName", query = "SELECT a FROM Applicants a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "Applicants.findByEmail", query = "SELECT a FROM Applicants a WHERE a.email = :email"),
    @NamedQuery(name = "Applicants.findByPhone", query = "SELECT a FROM Applicants a WHERE a.phone = :phone"),
    @NamedQuery(name = "Applicants.findByKeyword", query = "SELECT a FROM Applicants a WHERE a.keyword = :keyword"),
    @NamedQuery(name = "Applicants.findByComment", query = "SELECT a FROM Applicants a WHERE a.comment = :comment"),
    @NamedQuery(name = "Applicants.findByDateApply", query = "SELECT a FROM Applicants a WHERE a.dateApply = :dateApply"),
    @NamedQuery(name = "Applicants.findByStatus", query = "SELECT a FROM Applicants a WHERE a.status = :status"),
    @NamedQuery(name = "Applicants.findByMethod", query = "SELECT a FROM Applicants a WHERE a.method = :method")})
public class Applicants implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_applicants")
    private Integer idApplicants;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "middle_name")
    private String middleName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Lob
    @Column(name = "resume")
    private byte[] resume;
    @Basic(optional = false)
    @Column(name = "keyword")
    private String keyword;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "date_apply")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateApply;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "method")
    private String method;
    @JoinColumn(name = "id_job_vacancy", referencedColumnName = "id_job_vacancy")
    @ManyToOne(optional = false)
    private JobVacancy idJobVacancy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idApplicants")
    private Collection<ScheduleInterview> scheduleInterviewCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idApplicants")
    private Collection<ActionHistory> actionHistoryCollection;

    public Applicants() {
    }

    public Applicants(Integer idApplicants) {
        this.idApplicants = idApplicants;
    }

    public Applicants(Integer idApplicants, String firstName, String middleName, String lastName, String email, String phone, byte[] resume, String keyword, String comment, Date dateApply, String status, String method) {
        this.idApplicants = idApplicants;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
        this.keyword = keyword;
        this.comment = comment;
        this.dateApply = dateApply;
        this.status = status;
        this.method = method;
    }

    public Integer getIdApplicants() {
        return idApplicants;
    }

    public void setIdApplicants(Integer idApplicants) {
        this.idApplicants = idApplicants;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateApply() {
        return dateApply;
    }

    public void setDateApply(Date dateApply) {
        this.dateApply = dateApply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public JobVacancy getIdJobVacancy() {
        return idJobVacancy;
    }

    public void setIdJobVacancy(JobVacancy idJobVacancy) {
        this.idJobVacancy = idJobVacancy;
    }

    @XmlTransient
    public Collection<ScheduleInterview> getScheduleInterviewCollection() {
        return scheduleInterviewCollection;
    }

    public void setScheduleInterviewCollection(Collection<ScheduleInterview> scheduleInterviewCollection) {
        this.scheduleInterviewCollection = scheduleInterviewCollection;
    }

    @XmlTransient
    public Collection<ActionHistory> getActionHistoryCollection() {
        return actionHistoryCollection;
    }

    public void setActionHistoryCollection(Collection<ActionHistory> actionHistoryCollection) {
        this.actionHistoryCollection = actionHistoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApplicants != null ? idApplicants.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicants)) {
            return false;
        }
        Applicants other = (Applicants) object;
        if ((this.idApplicants == null && other.idApplicants != null) || (this.idApplicants != null && !this.idApplicants.equals(other.idApplicants))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Applicants[ idApplicants=" + idApplicants + " ]";
    }
    
}
