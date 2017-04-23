package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private long ID;

    @Column(name = "CreationDate", nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreationUser_ID", nullable = false)
    private long creationUser_ID;

    @Column(name = "UpdatingDate", nullable = false)
    private Date updatingDate;

    @ManyToOne
    @JoinColumn(name = "UpdatingUser_ID", nullable = false)
    private long updatingUser_ID;

    @Column(name = "SchoolName", nullable = false)
    private String schoolName;

    @ManyToOne
    @JoinColumn(name = "SchoolTown_ID", nullable = false)
    private String schoolTown_ID;

    public School() { }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
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

    public void setCreationUser_ID(long creationUser_ID) {
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

    public void setUpdatingUser_ID(long updatingUser_ID) {
        this.updatingUser_ID = updatingUser_ID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolTown_ID() {
        return schoolTown_ID;
    }

    public void setSchoolTown_ID(String schoolTown_ID) {
        this.schoolTown_ID = schoolTown_ID;
    }
}
