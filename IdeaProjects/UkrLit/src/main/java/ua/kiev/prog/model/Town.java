package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int ID;

    @Column(name = "CreationDate", nullable = false)
    private Date creationDate;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "CreationUser_ID", nullable = false)
    @Column(name = "CreationUser_ID", nullable = false)
    private int creationUser_ID;

    @Column(name = "UpdatingDate", nullable = false)
    private Date updatingDate;

    //@ManyToOne
    //@JoinColumn(name = "UpdatingUser_ID", nullable = false)
    @Column(name = "UpdatingUser_ID", nullable = false)
    private int updatingUser_ID;

    @Column(name = "TownName", nullable = false)
    private String townName;

    public Town() { }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getCreationUser_ID() {
        return creationUser_ID;
    }

    public void setCreationUser_ID(int creationUser_ID) {
        this.creationUser_ID = creationUser_ID;
    }

    public Date getUpdatingDate() {
        return updatingDate;
    }

    public void setUpdatingDate(Date updatingDate) {
        this.updatingDate = updatingDate;
    }

    public long getUpdatingUser_ID() {
        return updatingUser_ID;
    }

    public void setUpdatingUser_ID(int updatingUser_ID) {
        this.updatingUser_ID = updatingUser_ID;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
