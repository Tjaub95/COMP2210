/**
 * Represents a Book.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @author   James Cross (crossjh@auburn.edu)
 * @version  2015-02-23 
 *           2010-08-20
 *
 */
public class Book implements Comparable<Book> {
   private String author = new String("no title");
   private String title = new String("none");
   private int pages = 0; 
   
/**
 * Instantiates book with supplied fields.
 * 
 * @param theAuthor  the book's author
 * @param theTitle   the book's title
 * @param thePages   number of pages in the book
 *
 */
   public Book(String theAuthor, String theTitle, int thePages) {
      author = theAuthor; 
      title = theTitle;
      pages = thePages;  
   }
   
/**
 * Getter for the author field.
 *
 * @return  the book's author
 *
 */      
   public String getAuthor() {
      return this.author;
   }

/**
 * Getter for the title field.
 *
 * @return  the book's title
 *
 */      
   public String getTitle() {
      return this.title;
   }

/**
 * Compares this book to another book based on title.
 * Specifies the natural order of books.
 *
 * @param obj  Other book to be compared to this book
 * @return     zero if this book == obj,
 *             positive if this book > obj
 *             negative if this book < obj
 * @see        java.lang.Comparable
 */   
   @Override
   public int compareTo(Book other) {
      int cmp = this.title.compareTo(other.title);
      if (cmp == 0) {
         cmp = this.author.compareTo(other.author);
      }
      return cmp;
   }

/**
 * Checks equality of this book and another book based
 * on title. Consistent with compareTo above.
 *
 * @param obj  Other book to be compared to this book
 * @return     true if this book is logically equal to obj, false otherwise
 *
 */
   @Override
   public boolean equals(Object obj) {
      if (obj == this)
         return true;
      if (obj == null)
         return false;
      if (!(obj instanceof Book))
         return false;
         
      Book other = (Book) obj;
      return this.compareTo(other) == 0;
   }

/**
 * Creates a string representation of this book.
 *
 * @return     string representation of this book
 *
 */
   @Override   
   public String toString() {
      return ("\nAuthor: " + author
            + "\nTitle:  " + title
            + "\nPages:  " + pages
            + "\n");
   }
}
