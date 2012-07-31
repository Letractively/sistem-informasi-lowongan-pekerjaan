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
@Table(name = "manager")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manager.findAll", query = "SELECT m FROM Manager m"),
    @NamedQuery(name = "Manager.findByIdManager", query = "SELECT m FROM Manager m WHERE m.idManager = :idManager"),
    @NamedQuery(name = "Manager.findByNamaManager", query = "SELECT m FROM Manager m WHERE m.namaManager = :namaManager")})
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_manager")
    private Integer idManager;
    @Basic(optional = false)
    @Column(name = "nama_manager")
    private String namaManager;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idManager")
    private Collection<JobVacancy> jobVacancyCollection;

    public Manager() {
    }

    public Manager(Integer idManager) {
        this.idManager = idManager;
    }

    public Manager(Integer idManager, String namaManager) {
        this.idManager = idManager;
        this.namaManager = namaManager;
    }

    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    public String getNamaManager() {
        return namaManager;
    }

    public void setNamaManager(String namaManager) {
        this.namaManager = namaManager;
    }

    @XmlTransient
    public Collection<JobVacancy> getJobVacancyCollection() {
        return jobVacancyCollection;
    }

    public void setJobVacancyCollection(Collection<JobVacancy> jobVacancyCollection) {
        this.jobVacancyCollection = jobVacancyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idManager != null ? idManager.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manager)) {
            return false;
        }
        Manager other = (Manager) object;
        if ((this.idManager == null && other.idManager != null) || (this.idManager != null && !this.idManager.equals(other.idManager))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Manager[ idManager=" + idManager + " ]";
    }
    
}
