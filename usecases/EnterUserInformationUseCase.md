# Use Case: Enter User Information

## Actors
- **User**

---

## Preconditions
1. The user must be registered in the system.

---

## Main Flow
1. The **User** selects the option to enter personal details.
2. The **System** displays a form for entering personal details.
3. The **User** fills in the form with their name, contact information, and other required details.
4. The **User** submits the form.
5. The **System** validates the entered information.
6. The **System** saves the user's personal details.

---

## Postconditions
- The user's personal details are stored in the system.

---

## Alternative Flows

### **1. User Not Registered**
1. If the **System** detects that the user is not registered, it displays a message:
    - _"You need to register an account to proceed."_
2. The **System** provides a "Register" button or link to redirect the user to the registration page.
3. After completing registration, the **User** is redirected to the login page or logged in automatically.

---

### **2. Invalid Information Entered**
1. If the **User** enters invalid information, the **System** highlights the incorrect fields and displays specific error messages, such as:
    - _"Invalid email format."_
    - _"Password must be at least 8 characters."_
2. The **System** prompts the **User** to correct the information and resubmit the form.
3. If errors persist, the **System** may suggest contacting support for help.

