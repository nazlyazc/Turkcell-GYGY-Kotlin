package com.example.library_app.data.repository

import com.example.library_app.data.model.Book
import com.example.library_app.data.model.BorrowRecord
import com.example.library_app.data.supabase.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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


    suspend fun createBorrowRecord(record: BorrowRecord): Result<Unit> = runCatching {
        supabase.from("borrow_records").insert(record)
    }


    suspend fun updateBookStock(bookId: String, newStock: Int): Result<Unit> = runCatching {
        supabase.from("books")
            .update(mapOf("available_copies" to newStock)) {
                filter { eq("id", bookId) }
            }
    }


    suspend fun getUserBorrowRecords(userId: String): Result<List<BorrowRecord>> = runCatching {
        supabase.from("borrow_records")
            .select {
                filter { eq("student_id", userId) }
            }
            .decodeList<BorrowRecord>()
    }
    suspend fun returnBook(recordId: String, bookId: String, currentStock: Int) {
        // 1. İade tarihini güncelle
        supabase.from("borrow_records").update(
            {
                set("returned_at", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
            }
        ) {
            filter { eq("id", recordId) }
        }

        // 2. Stoku 1 artır
        supabase.from("books").update(
            {
                set("available_copies", currentStock + 1)
            }
        ) {
            filter { eq("id", bookId) }
        }
    }

}

