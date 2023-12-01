package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.service;


import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Livro;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
  private final LivroRepository livroRepository;
  public LivroService(LivroRepository livroRepository){
      this.livroRepository = livroRepository;
  }
    public List<Livro> findallLivro(){
        return livroRepository.findAll();
    }
    //Bloco de Procura
    public Optional<Livro> getLivroById(Long id){
        return  livroRepository.findById(id);
    }
    public List<Livro> findByAutor(String autor){
        return livroRepository.findByAutorContaining(autor);
    }


    public List<Livro> findByTitulo(String titulo){
        return livroRepository.findByTituloContaining(titulo);
    }


    //Salvando novo livro
    public Livro saveLivro(Livro livro){
        return livroRepository.save(livro);
    }
    //Atualizando o livro
    public Livro updateLivroById(Long id, Livro livroNovo) {
        Livro existingLivro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro not found with id: " + id));

        BeanUtils.copyProperties(livroNovo, existingLivro, "id");

        return livroRepository.save(existingLivro);
    }



    //Deletando livro pelo id
    public void deleteLivro(Long id){
        if(livroRepository.existsById(id)){
            livroRepository.deleteById(id);

        } else {
            throw  new RuntimeException("Livro not found with id: " + id);
        }
    }
}



