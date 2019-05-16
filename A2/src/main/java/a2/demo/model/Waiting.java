package a2.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "waiting_list")
public class Waiting {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "BookID")
    private int bookID;
    @Column(name = "username")
    private String username;

    public Waiting()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
