import java.util.ArrayList;
import java.util.List;

public class Library {
    private List <Book> books;
    public List <Book> getBooks(){
        return books;
    }
    public Library(){
        books=new ArrayList<>();
    }
    public void addBook(Book book){
        books.add(book);
    }

    public void borrowBook(int bookId){
        for (Book book:books){
            if(book.getBookId()==bookId){
                if(!book.isBorrowed()){
                    book.setBorrowed(true);
                    System.out.println("Book borrowed successfully!");
                }else{
                    System.out.println("Book is already borrowed");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void returnBook(int bookId){
        for (Book book:books){
            if(book.getBookId()==bookId){
                if(book.isBorrowed()){
                    book.setBorrowed(false);
                    System.out.println("Book return successfully!");
                }else{
                    System.out.println("Book was not borrowed");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    public void removeBook(int bookId){
        books.removeIf(book -> book.getBookId()==bookId);
    }


}
