package com.example.library_app.data.repository

import com.example.library_app.data.model.Book
import com.example.library_app.data.supabase.supabase
import io.github.jan.supabase.postgrest.postgrest

class BookRepository {
    suspend fun getAllBooks(): Result<List<Book>> = runCatching {
        supabase.postgrest["books"]
            .select()
            .decodeList<Book>()
    }

    suspend fun getBookById(id:String): Result<Book> = runCatching {
        supabase.postgrest["books"]
            .select { filter { eq("id",id) } }
            .decodeSingle<Book>()
    }

    suspend fun addBook(book: Book): Result<Unit> = runCatching {
        supabase.postgrest["books"].insert(book)
    }


    suspend fun updateBook(book: Book) = runCatching {
        supabase.postgrest["books"]
            .update(book) {
                filter { eq("id", book.id) }
            }
    }

    suspend fun deleteBook(bookId: String) = runCatching {
        supabase.postgrest["books"]
            .delete {
                filter { eq("id", bookId) }
            }
    }

    suspend fun searchBooks(query: String) = runCatching {
        supabase.postgrest["books"]
            .select {
                filter { ilike("title", "%$query%") }
            }
            .decodeList<Book>()
    }

}