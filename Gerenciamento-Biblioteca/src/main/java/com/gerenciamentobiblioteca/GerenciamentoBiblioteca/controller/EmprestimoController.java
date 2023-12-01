package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.controller;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Emprestimo;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Emprestimo> findall(){
        return emprestimoService.buscarEmprestimos();
    }
    @PostMapping("/realizaremprestimo")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Emprestimo realizarEmprestimo(@RequestParam Long alunoId, @RequestParam Long livroId) {
       return emprestimoService.realizarEmprestimo(alunoId, livroId);
    }
    @PutMapping("/devolverlivro/{emprestimoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Emprestimo devolverLivro(@PathVariable Long emprestimoId) {

       return emprestimoService.devolverLivro(emprestimoId);


    }

    @DeleteMapping("/{emprestimoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long emprestimoId){
        emprestimoService.deleteEmp(emprestimoId);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

/*
public class EmprestimoController {
    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService){
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/realizar-emprestimo")
    public String realizarEmprestimo(@RequestParam Long alunoId, @RequestParam Long livroId){
        if(emprestimoService.realizarEmprestimo(alunoId, livroId)){
            return String.valueOf(ResponseEntity.ok( "Empréstimo realizado com sucesso!"));
        } else {
            return String.valueOf(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar emprestimo. " +
                    "Verifique a disponibilidade do Livro"));
        }
    }
    @PostMapping("/devolver-livro")
    public String devolverLivro(@RequestParam Long emprestimoId){
        emprestimoService.devolverLivro(emprestimoId);
        return "Livro devolvido com sucesso!";
    }
}
*/