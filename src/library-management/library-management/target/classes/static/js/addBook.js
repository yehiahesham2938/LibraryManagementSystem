document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");

    form.addEventListener("submit", (e) => {
        let valid = true;

        // Book Name Validation
        const bookName = document.getElementById("bookName");
        if (bookName.value.trim() === "") {
            alert("Book Name is required.");
            valid = false;
        }

        // Author Name Validation
        const authorName = document.getElementById("authorName");
        if (authorName.value.trim() === "") {
            alert("Author Name is required.");
            valid = false;
        }

        // Publish Date Validation
        const publishDate = document.getElementById("publishDate");
        if (publishDate.value === "") {
            alert("Publish Date is required.");
            valid = false;
        }

        // ISBN Validation
        const isbn = document.getElementById("isbn");
        const isbnPattern = /^[0-9]{10,13}$/;
        if (!isbnPattern.test(isbn.value)) {
            alert("ISBN must be a 10 or 13-digit number.");
            valid = false;
        }

        // Cover Photo Validation
        const coverPhoto = document.getElementById("coverPhoto");
        if (coverPhoto.files.length === 0) {
            alert("Cover Photo is required.");
            valid = false;
        }

        // Stock Validation
        const stock = document.getElementById("stock");
        if (stock.value <= 0) {
            alert("Stock must be a positive number.");
            valid = false;
        }

        if (!valid) {
            e.preventDefault();
        }
    });
});
