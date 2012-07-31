/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WILLIAM
 */
@Entity
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByIdJob", query = "SELECT j FROM Job j WHERE j.idJob = :idJob"),
    @NamedQuery(name = "Job.findByJobTitle", query = "SELECT j FROM Job j WHERE j.jobTitle = :jobTitle")})
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_job")
    private Integer idJob;
    @Basic(optional = false)
    @Column(name = "job_title")
    private String jobTitle;
    @Basic(optional = false)
    @Column(name = "job_description")
    private String jobDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJob")
    private Collection<JobVacancy> jobVacancyCollection;

    public Job() {
    }

    public Job(Integer idJob) {
        this.idJob = idJob;
    }

    public Job(Integer idJob, String jobTitle) {
        this.idJob = idJob;
        this.jobTitle = jobTitle;
    }

    public Integer getIdJob() {
        return idJob;
    }

    public void setIdJob(Integer idJob) {
        this.idJob = idJob;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public String getJobDescription(){
        return jobDescription;
    }
    
    public void setJobDescription(String jobDescription){
        this.jobDescription = jobDescription;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJob != null ? idJob.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.idJob == null && other.idJob != null) || (this.idJob != null && !this.idJob.equals(other.idJob))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Job[ idJob=" + idJob + " ]";
    }
    
}
