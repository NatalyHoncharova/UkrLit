package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TestResults")
public class TestResult {
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

    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private long user_ID;

    @ManyToOne
    @JoinColumn(name = "Test_ID", nullable = false)
    private long test_ID;

    @Column(name = "Score", nullable = false)
    private int score;

    @Column(name = "TestDate", nullable = false)
    private Date testDate;

    public TestResult() { };

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

    public long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(long user_ID) {
        this.user_ID = user_ID;
    }

    public long getTest_ID() {
        return test_ID;
    }

    public void setTest_ID(long test_ID) {
        this.test_ID = test_ID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
}
