package br.com.diego.kotlincrud.controller

import br.com.diego.kotlincrud.model.Note
import br.com.diego.kotlincrud.repository.NoteRepository
import br.com.diego.kotlincrud.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("notes")
class NoteController(private val noteService: NoteService) {

    @GetMapping
    fun list(): ResponseEntity<List<Note>> {
        //return listOf(Note("Leitura", "Livro de Spring Boot"), Note("Pesquisa", "Aprendendo docker"))
        val allNotes = noteService.listAll()
        return ResponseEntity.ok(allNotes)
    }

    @PostMapping
    fun add(@RequestBody nota: Note): ResponseEntity<Note>{
        //return nota
        val addNote = noteService.save(nota)
        return ResponseEntity.ok(addNote)
    }

    @GetMapping("{titulo}")
    fun findByTitulo(@PathVariable titulo: String): ResponseEntity<Note>{
        val noteByTitulo = noteService.listByTitulo(titulo)
        return ResponseEntity.ok(noteByTitulo)
    }

    @GetMapping("/find/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Optional<Note>>{
        val noteById = noteService.listById(id)
        return ResponseEntity.ok(noteById)
    }

    @PutMapping("/alter/{id}")
    fun alter(@PathVariable id: Long, @RequestBody note: Note): ResponseEntity<Note>{

        if (noteService.existsById(id)) {
            val alterNote = noteService.alter(id, note)
            return ResponseEntity.ok(alterNote)
        }

        return ResponseEntity(HttpStatus.NOT_FOUND)

    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit>{

        if(noteService.existsById(id)){
            val deleteNote = noteService.deleteById(id)
            return ResponseEntity.ok(deleteNote)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

}