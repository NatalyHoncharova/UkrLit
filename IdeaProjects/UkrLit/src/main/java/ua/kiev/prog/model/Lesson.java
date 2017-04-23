package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Lessons")
public class Lesson {
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

    @Column(name = "LessonName", nullable = false)
    private String lessonName;

    @Column(name = "Description")
    private String description;

    @JoinColumn(name = "Author_ID", nullable = false)
    private long author_ID;

    public Lesson() { }

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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAuthor_ID() {
        return author_ID;
    }

    public void setAuthor_ID(long author_ID) {
        this.author_ID = author_ID;
    }
}
