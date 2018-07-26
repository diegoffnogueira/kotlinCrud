package br.com.diego.kotlincrud.service

import br.com.diego.kotlincrud.model.Note
import br.com.diego.kotlincrud.repository.NoteRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteService(private val noteRepository: NoteRepository) {

    fun listAll(): List<Note> {
        //return listOf(Note("Leitura", "Livro de Spring Boot"), Note("Pesquisa", "Aprendendo docker"))
        return noteRepository.findAll().toList()
    }

    fun save(nota: Note): Note {
        //return nota
        return noteRepository.save(nota)
    }

    fun listByTitulo(titulo: String): Note {
        return noteRepository.findByTitulo(titulo)
    }

    fun listById( id: Long): Optional<Note> {
        return noteRepository.findById(id)
    }

    fun existsById(id: Long): Boolean {
        return noteRepository.existsById(id)
    }

    fun alter(id: Long, note: Note): Note {

        val safeNote = note.copy(id = id)
        return noteRepository.save(safeNote)

    }

    fun deleteById(id: Long) {
        noteRepository.deleteById(id)
    }

}