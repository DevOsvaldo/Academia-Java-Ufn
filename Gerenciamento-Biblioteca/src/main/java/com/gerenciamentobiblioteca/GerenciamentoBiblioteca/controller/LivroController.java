package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.controller;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Livro;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.SituacaoLivro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.service.LivroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private final LivroService livroService;
    @Autowired
    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Livro> findallLivro(){
       return livroService.findallLivro();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable Long id){
        Optional<Livro>livroOptional = livroService.getLivroById(id);
        return livroOptional.map(livro -> new ResponseEntity<>(livro, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAutor")
    public List<Livro> getLivroByAutor(@RequestParam String autor){
        return livroService.findByAutor(autor);

    }
    @GetMapping("/getTitulo")
    public List<Livro> getLivroByTitulo(@RequestParam String titulo){
        return livroService.findByTitulo(titulo);
    }


    //Sem passar por parametro
    @PostMapping
    public Livro createNewLivro(@RequestBody Livro livro) {
        livro.setStatusLivro(SituacaoLivro.DISPONIVEL);
        return livroService.saveLivro(livro);
    }

    @PutMapping("/{id}")
    public Livro updateLivro(@PathVariable Long id, @RequestBody @Valid Livro livroNovo) {
        return livroService.updateLivroById(id,livroNovo);
    }
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Long id){
        livroService.deleteLivro(id);
    }

}
