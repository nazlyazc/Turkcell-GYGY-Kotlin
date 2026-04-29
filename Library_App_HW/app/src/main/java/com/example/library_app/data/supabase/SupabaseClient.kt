package com.example.library_app.data.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import com.example.library_app.BuildConfig

val supabase = createSupabaseClient(
    supabaseKey = BuildConfig.SUPABASE_ANON_KEY,
    supabaseUrl = BuildConfig.SUPABASE_URL
) {
    install(Postgrest)
    install(Auth)
}