/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author onkar
 */
@Entity
@Table(name = "CINEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cinema.findAll", query = "SELECT c FROM Cinema c")
    , @NamedQuery(name = "Cinema.findByCinemaName", query = "SELECT c FROM Cinema c WHERE c.cinemaName = :cinemaName")
    , @NamedQuery(name = "Cinema.findByCinemaId", query = "SELECT c FROM Cinema c WHERE c.cinemaId = :cinemaId")
    , @NamedQuery(name = "Cinema.findByLocation", query = "SELECT c FROM Cinema c WHERE c.location = :location")
//    , @NamedQuery(name = "Cinema.findByImplicatJoin",query = "")
})
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "CINEMA_NAME")
    private String cinemaName;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CINEMA_ID")
    private Integer cinemaId;
    @Size(max = 50)
    @Column(name = "LOCATION")
    private String location;
    @OneToMany(mappedBy = "cinemaId")
    private Collection<Memoir> memoirCollection;

    public Cinema() {
    }

    public Cinema(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Memoir> getMemoirCollection() {
        return memoirCollection;
    }

    public void setMemoirCollection(Collection<Memoir> memoirCollection) {
        this.memoirCollection = memoirCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cinemaId != null ? cinemaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cinema)) {
            return false;
        }
        Cinema other = (Cinema) object;
        if ((this.cinemaId == null && other.cinemaId != null) || (this.cinemaId != null && !this.cinemaId.equals(other.cinemaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "memoir.awt.Cinema[ cinemaId=" + cinemaId + " ]";
    }
    
}
