import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Author {
    private String firstName;
    private String lastName;
    private String patronymic;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Saratov")
    private Date hiredDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Saratov")
    private Date firedDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Date getFiredDate() {
        return firedDate;
    }

    public void setFiredDate(Date firedDate) {
        this.firedDate = firedDate;
    }

    private Author(){}

    static public Author Hire(String firstName, String lastName, String patronymic) {
        Author newAuthor = Hire(firstName, lastName);
        newAuthor.patronymic = patronymic;

        return newAuthor;
    }

    static public Author Hire(String firstName, String lastName) {
        Author newAuthor = new Author();
        newAuthor.hiredDate = new Date();
        newAuthor.firstName = firstName;
        newAuthor.lastName = lastName;
        newAuthor.firedDate = null;
        newAuthor.patronymic = null;

        return newAuthor;
    }

    public void Fire(String firstName, String lastName) {
        this.firedDate = new Date();
    }

    @Override
    public String toString() {
        return String.format("First name: " + firstName +
                ", Last name: " + lastName +
                ", Patronimyc: " + ((patronymic == null) ? "-" : patronymic) +
                ", Hired: " + hiredDate +
                ", Fired: " + ((firedDate == null) ? "-" : firedDate));
    }
}