public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book (int bookId, String title, String author){
        this.bookId=bookId;
        this.title=title;
        this.author=author;
        this.isBorrowed=false;
    }

    //Getters and Setters
    public int getBookId(){
        return bookId;
    }
    public String getTitle(){
        return title;
    }
    public boolean isBorrowed(){
        return isBorrowed;
    }
    public void setBorrowed(boolean borrowed){
        isBorrowed=borrowed;
    }

    public String bookDetails(){
        return "Book ID: "+bookId+
                ",Title: "+title+
                ",Author: "+author+
                ",Status: "+(isBorrowed? "Borrowed":"Available");
    }
}