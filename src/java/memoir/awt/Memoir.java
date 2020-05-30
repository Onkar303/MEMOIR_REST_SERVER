/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author onkar
 */
@Entity
@Table(name = "MEMOIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoir.findAll", query = "SELECT m FROM Memoir m")
    , @NamedQuery(name = "Memoir.findByMemoirId", query = "SELECT m FROM Memoir m WHERE m.memoirId = :memoirId")
    , @NamedQuery(name = "Memoir.findByMovieName", query = "SELECT m FROM Memoir m WHERE m.movieName = :movieName")
    , @NamedQuery(name = "Memoir.findByMovieReleaseDate", query = "SELECT m FROM Memoir m WHERE m.movieReleaseDate = :movieReleaseDate")
    , @NamedQuery(name = "Memoir.findByMovieWatchTime", query = "SELECT m FROM Memoir m WHERE m.movieWatchTime = :movieWatchTime")
    , @NamedQuery(name = "Memoir.findByMovieWatchDate", query = "SELECT m FROM Memoir m WHERE m.movieWatchDate = :movieWatchDate")
    , @NamedQuery(name = "Memoir.findByMovieComment", query = "SELECT m FROM Memoir m WHERE m.movieComment = :movieComment")
    , @NamedQuery(name = "Memoir.findByCinemaNameAndMovieNameStatic",query = "select m from Memoir m where m.cinemaId.cinemaName = :cinemaName and m.movieName = :movieName ")})
public class Memoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEMOIR_ID")
    private Integer memoirId;
    @Size(max = 50)
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "MOVIE_RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date movieReleaseDate;
    @Column(name = "MOVIE_WATCH_TIME")
    @Temporal(TemporalType.TIME)
    private Date movieWatchTime;
    @Column(name = "MOVIE_WATCH_DATE")
    @Temporal(TemporalType.DATE)
    private Date movieWatchDate;
    @Size(max = 100)
    @Column(name = "MOVIE_IMAGE")
    private String movieImage;
    @Size(max = 500)
    @Column(name = "MOVIE_COMMENT")
    private String movieComment;
    @Size(max = 500)
    @Column(name = "MOVIE_GENERE")
    private String movieGenere;
    @JoinColumn(name = "CINEMA_ID", referencedColumnName = "CINEMA_ID")
    @ManyToOne
    private Cinema cinemaId;
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne
    private Person personId;
    @JoinColumn(name = "MOVIE_RATING", referencedColumnName = "STAR")
    @ManyToOne
    private Rating movieRating;

    public Memoir() {
    }

    public Memoir(Integer memoirId, String movieName, Date movieReleaseDate, Date movieWatchTime, Date movieWatchDate,Rating movieRating ,String movieImage,String movieComment,String movieGenere, Cinema cinemaId, Person personId) {
        this.memoirId = memoirId;
        this.movieName = movieName;
        this.movieReleaseDate = movieReleaseDate;
        this.movieWatchTime = movieWatchTime;
        this.movieWatchDate = movieWatchDate;
        this.movieComment = movieComment;
        this.cinemaId = cinemaId;
        this.personId = personId;
        this.movieRating = movieRating;
        this.movieImage = movieImage;
        this.movieGenere = movieGenere;
    }
    
    

    public Memoir(Integer memoirId) {
        this.memoirId = memoirId;
    }

    public Integer getMemoirId() {
        return memoirId;
    }

    public void setMemoirId(Integer memoirId) {
        this.memoirId = memoirId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(Date movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public Date getMovieWatchTime() {
        return movieWatchTime;
    }

    public void setMovieWatchTime(Date movieWatchTime) {
        this.movieWatchTime = movieWatchTime;
    }

    public Date getMovieWatchDate() {
        return movieWatchDate;
    }

    public void setMovieWatchDate(Date movieWatchDate) {
        this.movieWatchDate = movieWatchDate;
    }

    public String getMovieComment() {
        return movieComment;
    }

    public void setMovieComment(String movieComment) {
        this.movieComment = movieComment;
    }

    public Cinema getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Cinema cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Rating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Rating movieRating) {
        this.movieRating = movieRating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memoirId != null ? memoirId.hashCode() : 0);
        return hash;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieGenere() {
        return movieGenere;
    }

    public void setMovieGenere(String movieGenere) {
        this.movieGenere = movieGenere;
    }
    
    
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoir)) {
            return false;
        }
        Memoir other = (Memoir) object;
        if ((this.memoirId == null && other.memoirId != null) || (this.memoirId != null && !this.memoirId.equals(other.memoirId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "memoir.awt.Memoir[ memoirId=" + memoirId + " ]";
    }
    
}
