package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Answers")
public class Answer {
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
    @JoinColumn(name = "Question_ID", nullable = false)
    private long question_ID;

    @Column(name = "AnswerText", nullable = false)
    private String answerText;

    @Column(name = "Correct", nullable = false)
    private boolean correct;

    public Answer() { }

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

    public long getQuestion_ID() {
        return question_ID;
    }

    public void setQuestion_ID(long question_ID) {
        this.question_ID = question_ID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
