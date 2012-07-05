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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "job_vacancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobVacancy.findAll", query = "SELECT j FROM JobVacancy j"),
    @NamedQuery(name = "JobVacancy.findByIdJobVacancy", query = "SELECT j FROM JobVacancy j WHERE j.idJobVacancy = :idJobVacancy"),
    @NamedQuery(name = "JobVacancy.findByIdJob", query = "SELECT j FROM JobVacancy j WHERE j.idJob = :idJob"),
    @NamedQuery(name = "JobVacancy.findByDescription", query = "SELECT j FROM JobVacancy j WHERE j.description = :description"),
    @NamedQuery(name = "JobVacancy.findByPostDate", query = "SELECT j FROM JobVacancy j WHERE j.postDate = :postDate"),
    @NamedQuery(name = "JobVacancy.findByStatus", query = "SELECT j FROM JobVacancy j WHERE j.status = :status"),
    @NamedQuery(name = "JobVacancy.findByTitleVacancy", query = "SELECT j FROM JobVacancy j WHERE j.titleVacancy = :titleVacancy"),
    @NamedQuery(name = "JobVacancy.findByNumberPosition", query = "SELECT j FROM JobVacancy j WHERE j.numberPosition = :numberPosition")})
public class JobVacancy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_job_vacancy")
    private String idJobVacancy;
    @Basic(optional = false)
    @Column(name = "id_job")
    private String idJob;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "post_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "title_vacancy")
    private String titleVacancy;
    @Basic(optional = false)
    @Column(name = "number_position")
    private int numberPosition;
    @OneToMany(mappedBy = "idJobVacancy")
    private Collection<Applicants> applicantsCollection;
    @JoinColumn(name = "id_manager", referencedColumnName = "id_manager")
    @ManyToOne(optional = false)
    private Manager idManager;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jobVacancy1")
    private JobVacancy jobVacancy;
    @JoinColumn(name = "id_job_vacancy", referencedColumnName = "id_job_vacancy", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private JobVacancy jobVacancy1;

    public JobVacancy() {
    }

    public JobVacancy(String idJobVacancy) {
        this.idJobVacancy = idJobVacancy;
    }

    public JobVacancy(String idJobVacancy, String idJob, String description, Date postDate, String status, String titleVacancy, int numberPosition) {
        this.idJobVacancy = idJobVacancy;
        this.idJob = idJob;
        this.description = description;
        this.postDate = postDate;
        this.status = status;
        this.titleVacancy = titleVacancy;
        this.numberPosition = numberPosition;
    }

    public String getIdJobVacancy() {
        return idJobVacancy;
    }

    public void setIdJobVacancy(String idJobVacancy) {
        this.idJobVacancy = idJobVacancy;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitleVacancy() {
        return titleVacancy;
    }

    public void setTitleVacancy(String titleVacancy) {
        this.titleVacancy = titleVacancy;
    }

    public int getNumberPosition() {
        return numberPosition;
    }

    public void setNumberPosition(int numberPosition) {
        this.numberPosition = numberPosition;
    }

    @XmlTransient
    public Collection<Applicants> getApplicantsCollection() {
        return applicantsCollection;
    }

    public void setApplicantsCollection(Collection<Applicants> applicantsCollection) {
        this.applicantsCollection = applicantsCollection;
    }

    public Manager getIdManager() {
        return idManager;
    }

    public void setIdManager(Manager idManager) {
        this.idManager = idManager;
    }

    public JobVacancy getJobVacancy() {
        return jobVacancy;
    }

    public void setJobVacancy(JobVacancy jobVacancy) {
        this.jobVacancy = jobVacancy;
    }

    public JobVacancy getJobVacancy1() {
        return jobVacancy1;
    }

    public void setJobVacancy1(JobVacancy jobVacancy1) {
        this.jobVacancy1 = jobVacancy1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJobVacancy != null ? idJobVacancy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobVacancy)) {
            return false;
        }
        JobVacancy other = (JobVacancy) object;
        if ((this.idJobVacancy == null && other.idJobVacancy != null) || (this.idJobVacancy != null && !this.idJobVacancy.equals(other.idJobVacancy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitas.JobVacancy[ idJobVacancy=" + idJobVacancy + " ]";
    }
    
}
