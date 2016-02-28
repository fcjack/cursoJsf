package br.edu.fa7.cursojsf.service;

import br.edu.fa7.cursojsf.model.Estado;
import br.edu.fa7.cursojsf.repository.EstadoRepository;

import javax.inject.Inject;
import java.util.List;

public class EstadoService {

    @Inject
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public void save(Estado estado) {
        estadoRepository.save(estado);
    }

    public void remove(Integer id) {
        estadoRepository.remove(id);
    }

    public Estado findById(Integer id) {
        return estadoRepository.findById(id);
    }
}
