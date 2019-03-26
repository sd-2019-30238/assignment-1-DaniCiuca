package bussinessLayer.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private Date releaseDate;
    private int available;
    private List<User> waitingList;

    public Book(String title, String author, String genre, Date releaseDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.available = 1;
        this.waitingList = new ArrayList<User>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<User> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<User> waitingList) {
        this.waitingList = waitingList;
    }
}
