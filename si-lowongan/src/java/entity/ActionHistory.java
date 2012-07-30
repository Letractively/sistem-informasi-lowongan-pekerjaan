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
@Table(name = "action_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionHistory.findAll", query = "SELECT a FROM ActionHistory a"),
    @NamedQuery(name = "ActionHistory.findByIdActionHistory", query = "SELECT a FROM ActionHistory a WHERE a.idActionHistory = :idActionHistory"),
    @NamedQuery(name = "ActionHistory.findByDateAction", query = "SELECT a FROM ActionHistory a WHERE a.dateAction = :dateAction"),
    @NamedQuery(name = "ActionHistory.findByAction", query = "SELECT a FROM ActionHistory a WHERE a.action = :action"),
    @NamedQuery(name = "ActionHistory.findByNotes", query = "SELECT a FROM ActionHistory a WHERE a.notes = :notes")})
public class ActionHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_action_history")
    private Integer idActionHistory;
    @Basic(optional = false)
    @Column(name = "date_action")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAction;
    @Basic(optional = false)
    @Column(name = "action")
    private String action;
    @Basic(optional = false)
    @Column(name = "notes")
    private String notes;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User idUser;
    @JoinColumn(name = "id_applicants", referencedColumnName = "id_applicants")
    @ManyToOne
    private Applicants idApplicants;

    public ActionHistory() {
    }

    public ActionHistory(Integer idActionHistory) {
        this.idActionHistory = idActionHistory;
    }

    public ActionHistory(Integer idActionHistory, Date dateAction, String action, String notes) {
        this.idActionHistory = idActionHistory;
        this.dateAction = dateAction;
        this.action = action;
        this.notes = notes;
    }

    public Integer getIdActionHistory() {
        return idActionHistory;
    }

    public void setIdActionHistory(Integer idActionHistory) {
        this.idActionHistory = idActionHistory;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        hash += (idActionHistory != null ? idActionHistory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionHistory)) {
            return false;
        }
        ActionHistory other = (ActionHistory) object;
        if ((this.idActionHistory == null && other.idActionHistory != null) || (this.idActionHistory != null && !this.idActionHistory.equals(other.idActionHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ActionHistory[ idActionHistory=" + idActionHistory + " ]";
    }
    
}
