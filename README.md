# LibraryManagementSystem

## 1.1 Topic Selection and Requirenments Elicitation
    Project Topic: Library Management Sysem.
   
    Problem: Traditional libraries often rely on manual tracking of books, users, and transactions, We will address this problem by automating these processes.
__________
### **Functional Requirenments:**
1) The user should be able to insert his personal details such as name, contact Information, etc..
2) Library admin should be able to add, update, and remove any book from the list of the books inside the library, including details such as title, ISBN, author, publish date.
3) The user should be able to borrow and return book with due dates he assigns when borrowing the book. 
4) The users should be able to search for books inside the library.
5) Admins of the library should have a restricted access to the library system with login authentication to manage the system.
6) Users should be able to view their borrowing history.
7) The admin should be able to view system logs which includes all recent activites occured through the system like borrowed book, returned book, and users already on the system.
____________
### **Non-Functional Requirenments:**
1) System should be able to handle at least 50 concurrent users.
2) System should have a quick response time for queries with a maximum 3 seconds response time.
3) The system should be available 24/7.
4) The system should be well documented to facilitate easy updates, bug fixes, and maintenance.
5) The system should follow the OWASP Princibles to ensure security best practices are applied.

## 1.2 Use Case Models
###  Use Case Diagram
<p align="center">
  <img src="imgs/UseCaseDiagram.png" alt="Use Case Diagram">
</p>
_________________________________________________________________________________________

### detailed use cases
1) **Enter User Information**

    #### Actors: 
        User

    #### Preconditions 
        The user must be registered in the system.

    #### Main Flow
        1. The user selects the option to enter personal details.
        2. The system displays a form for entering personal details.
        3. The user fills in the form with their name, contact information, and other required details.
        4. The user submits the form.
        5. The system validates the entered information.
        6. The system saves the user's personal details.

    #### Postconditions 
        The user's personal details are stored in the system.

    #### Alternative Flows
        - If the user is not registered, the system prompts the user to register first.
        - If the entered information is invalid, the system displays an error message and prompts the user to correct the information.

2) **Borrow Book**

    #### Actors: 
        User

    #### Preconditions 
        The user must be registered and logged into the system.
        The book must be available in the library inventory.

    #### Main Flow
        1. The user selects the option to borrow a book.
        2. The system displays a list of available books.
        3. The user selects a book to borrow.
        4. The system prompts the user to enter the due date for returning the book.
        5. The user enters the due date.
        6. The system validates the due date.
        7. The system updates the inventory to mark the book as borrowed.
        8. The system records the borrowing transaction with the user's details and due date.

    #### Postconditions 
        The book is marked as borrowed in the system.
        The borrowing transaction is recorded with the due date.

    #### Alternative Flows
        - If the user is not registered or logged in, the system prompts the user to register or log in.
        - If the book is not available, the system displays a message indicating the book is not available for borrowing.
    
3) **Return Book**

    #### Actors: 
        User

    #### Preconditions 
        The user must be registered and logged into the system.
        The book must be currently borrowed by the user.

    #### Main Flow
        1. The user selects the option to return a book.
        2. The system displays a list of books currently borrowed by the user.
        3. The user selects the book to return.
        4. The system prompts the user to confirm the return.
        5. The user confirms the return.
        6. The system updates the inventory to mark the book as returned.
        7. The system records the return transaction with the user's details.

    #### Postconditions 
        The book is marked as returned in the system.
        The return transaction is recorded.

    #### Alternative Flows
        - If the user is not registered or logged in, the system prompts the user to register or log in.
        - If the book is not currently borrowed by the user, the system displays a message indicating the book cannot be returned.

4) **Search Book**

    #### Actors: 
        User

    #### Preconditions 
        The user must be registered and logged into the system.

    #### Main Flow
        1. The user selects the option to search for a book.
        2. The system displays a search form.
        3. The user enters search criteria such as title, author, or ISBN.
        4. The user submits the search form.
        5. The system processes the search query.
        6. The system displays a list of books that match the search criteria.

    #### Postconditions 
        The user views the list of books that match the search criteria.

    #### Alternative Flows
        - If no books match the search criteria, the system displays a message indicating no results were found.


5) **Admin Login**

    #### Actors: 
        Admin

    #### Preconditions 
        The admin must have valid login credentials.

    #### Main Flow
        1. The admin selects the option to log in.
        2. The system displays a login form.
        3. The admin enters their username and password.
        4. The admin submits the login form.
        5. The system validates the entered credentials.
        6. The system grants access to the admin if the credentials are valid.

    #### Postconditions 
        The admin is logged into the system and can access admin functionalities.

    #### Alternative Flows
        - If the entered credentials are invalid, the system displays an error message and prompts the admin to re-enter the credentials.

6) **Add/Update/Remove Book**

    #### Actors: 
        Admin

    #### Preconditions 
        The admin must be logged into the system.

    #### Main Flow
        1. The admin selects the option to add, update, or remove a book.
        2. The system displays a form for entering book details (title, ISBN, author, publish date).
        3. The admin fills in the form with the necessary details.
        4. The admin submits the form.
        5. The system validates the entered information.
        6. The system updates the inventory accordingly (adds a new book, updates existing book details, or removes a book).

    #### Postconditions 
        The inventory is updated with the new book details, updated book details, or the book is removed from the inventory.

    #### Alternative Flows
        - If the admin is not logged in, the system prompts the admin to log in.
        - If the entered information is invalid, the system displays an error message and prompts the admin to correct the information.
        - If the book to be updated or removed does not exist, the system displays a message indicating the book is not found.

7) **View System Logs**

    #### Actors: 
        Admin

    #### Preconditions 
        The admin must be logged into the system.

    #### Main Flow
        1. The admin selects the option to view system logs.
        2. The system displays a list of recent activities, including borrowed books, returned books, and user registrations.
        3. The admin can filter the logs based on date, activity type, or user.
        4. The admin reviews the filtered logs.

    #### Postconditions 
        The admin views the system logs and recent activities.

    #### Alternative Flows
        - If the admin is not logged in, the system prompts the admin to log in.
        - If there are no logs available, the system displays a message indicating no recent activities.

        
## 1.3 Domain Model

