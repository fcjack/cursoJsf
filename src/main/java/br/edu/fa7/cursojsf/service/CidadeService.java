package br.edu.fa7.cursojsf.service;

import br.edu.fa7.cursojsf.model.Cidade;
import br.edu.fa7.cursojsf.repository.CidadeRepository;

import javax.inject.Inject;

public class CidadeService {

    @Inject
    private CidadeRepository cidadeRepository;

    public void save(Cidade cidade) {
        cidadeRepository.save(cidade);
    }

    public void remove(Integer id) {
        cidadeRepository.remove(id);
    }

    public Cidade findById(Integer id) {
        return cidadeRepository.findById(id);
    }
}
