# Use Case: Borrow Book

## Actors
- **Library Member**: The user borrowing the book.
- **System**: The library management system handling the transaction.

---

## Preconditions
1. The user must be logged in.
2. The book must be available in the catalog.

---

## Main Flow
1. **Library Member** selects a book to borrow.
2. **System** checks the availability of the selected book.
3. **Library Member** confirms borrowing.
4. **System**:
    - Records the borrowing transaction.
    - Updates the book status to "borrowed."

---

## Postconditions
- The borrowing transaction is logged in the system.
- The status of the book is updated to "borrowed."

---

## Alternate Flows
- **If the book is unavailable**:
    1. The **System** prompts the **Library Member** to reserve the book.
    2. The **Library Member** confirms the reservation, which is recorded in the system.
