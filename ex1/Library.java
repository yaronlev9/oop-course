/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to be able
 * to check out books, if a copy of the requested book is available.
 */
class Library {

    /**
     * The maximum number of books in the library.
     */
    final int maxNumberOfBooks;

    /**
     * The maximum number of borrowed books available for each patron.
     */
    final int maxNumberOfBorrowedBooks;

    /**
     * The maximum number of patrons.
     */
    final int maxNumberOfPatrons;

    /**
     * an array containing the books in the library.
     */
    Book[] booksArray;

    /**
     * an array containing the patrons registered to the library.
     */
    Patron[] patronsArray;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new library with the given characteristic.
     *
     * @param maxBookCapacity   The maximal number of books this library can hold.
     * @param maxBorrowedBooks  The maximal number of books this library allows a single patron to borrow
     *                          at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        maxNumberOfBooks = maxBookCapacity;
        maxNumberOfBorrowedBooks = maxBorrowedBooks;
        maxNumberOfPatrons = maxPatronCapacity;
        booksArray = new Book[maxNumberOfBooks];
        patronsArray = new Patron[maxNumberOfPatrons];
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book - The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        int bookId = getBookId(book);
        if (bookId != -1) {
            return bookId;
        } else {
            for (int i = 0; i < booksArray.length; i++) {
                if (booksArray[i] == null) {
                    booksArray[i] = book;
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     *
     * @param bookId - The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        if (bookId < 0 || bookId >= maxNumberOfBooks || booksArray[bookId] == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     *
     * @param book - The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i = 0; i < booksArray.length; i++) {
            if (booksArray[i] == book) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId - The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        boolean validBook = isBookIdValid(bookId);
        if (validBook && booksArray[bookId].getCurrentBorrowerId() == -1) {
            return true;
        }
        return false;
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron - The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully registered
     * or if the patron was already registered. a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        int patronId = getPatronId(patron);
        if (patronId != -1) {
            return patronId;
        } else {
            for (int i = 0; i < patronsArray.length; i++) {
                if (patronsArray[i] == null) {
                    patronsArray[i] = patron;
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     *
     * @param patronId - The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        if (patronId < 0 || patronId >= maxNumberOfPatrons || patronsArray[patronId] == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     *
     * @param patron - The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i = 0; i < patronsArray.length; i++) {
            if (patronsArray[i] == patron) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if this book is
     * available, the given patron isn't already borrowing the maximal number of books allowed, and if the patron
     * will enjoy this book.
     *
     * @param bookId   - The id number of the book to borrow.
     * @param patronId - The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        boolean validBook = isBookIdValid(bookId);
        boolean validPatron = isPatronIdValid(patronId);
        boolean reachMax = isPatronReachMax(patronId);
        if (!validBook || !validPatron || reachMax) {
            return false;
        } else if (booksArray[bookId].getCurrentBorrowerId() == -1
                && patronsArray[patronId].willEnjoyBook(booksArray[bookId])) {
            booksArray[bookId].setBorrowerId(patronId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Searches for all the borrowed books by the patron and returns true if the number borrowed reaches max otherwise
     * returns false.
     *
     * @param patronId - The id number of the patron that will borrow the book.
     * @return true if the patron reached maximum borrowed books false otherwise.
     */
    boolean isPatronReachMax(int patronId) {
        int counter = 0;
        for (int i = 0; i < booksArray.length; i++) {
            if (booksArray[i] != null && booksArray[i].getCurrentBorrowerId() == patronId) {
                counter++;
                if (counter == maxNumberOfBorrowedBooks) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return the given book.
     *
     * @param bookId - The id number of the book to return.
     */
    void returnBook(int bookId) {
        boolean validBook = isBookIdValid(bookId);
        if (validBook) {
            booksArray[bookId].setBorrowerId(-1);
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he will enjoy,
     * if any such exist.
     *
     * @param patronId - The id number of the patron to suggest the book to.
     * @return The available book the patron with the given ID will enjoy the most. Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        boolean validPatron = isPatronIdValid(patronId);
        if (!validPatron) {
            return null;
        }
        int bookId = availableBookWithHighestValue(patronId);
        if (patronsArray[patronId].willEnjoyBook(booksArray[bookId])) {
            return booksArray[bookId];
        } else {
            return null;
        }
    }

    /**
     * finds the id of the book the patron with the given id will give the highest literary value.
     *
     * @param patronId - The id number of the patron to suggest the book to.
     * @return The available book id the patron with the given ID will enjoy the most.
     */
    int availableBookWithHighestValue(int patronId) {
        int maxLiteraryValue = 0;
        int bookId = 0;
        for (int i = 0; i < booksArray.length; i++) {
            if (booksArray[i].getCurrentBorrowerId() != -1) {
                continue;
            }
            int literaryValue = patronsArray[patronId].getBookScore(booksArray[i]);
            if (literaryValue > maxLiteraryValue) {
                maxLiteraryValue = literaryValue;
                bookId = i;
            }
        }
        return bookId;
    }

}