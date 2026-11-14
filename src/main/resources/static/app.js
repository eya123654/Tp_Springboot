const booksBody = document.getElementById('books-body');
const form = document.getElementById('book-form');
const searchForm = document.getElementById('search-form');

async function fetchJson(url, options = {}) {
    const response = await fetch(url, {
        credentials: 'same-origin',
        ...options
    });

    if (response.status === 401 || response.status === 403) {
        window.location.href = '/login.html';
        return null;
    }

    return response.json();
}

function renderBooks(books) {
    booksBody.innerHTML = books.map(book => `
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.isbn}</td>
            <td>${book.available ? 'Oui' : 'Non'}</td>
        </tr>
    `).join('');
}

async function loadBooks(url = '/api/books') {
    const books = await fetchJson(url);
    if (books) {
        renderBooks(books);
    }
}

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const book = {
        title: document.getElementById('title').value,
        author: document.getElementById('author').value,
        isbn: document.getElementById('isbn').value,
        available: true
    };

    await fetchJson('/api/books', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
    });

    form.reset();
    await loadBooks();
});

searchForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const title = document.getElementById('search-title').value.trim();
    const author = document.getElementById('search-author').value.trim();
    const category = document.getElementById('search-category').value.trim();

    const params = new URLSearchParams();
    if (title) params.append('title', title);
    if (author) params.append('author', author);
    if (category) params.append('category', category);

    await loadBooks(`/api/books/search?${params.toString()}`);
});

loadBooks();
