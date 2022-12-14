package org.soulcodeacademy.empresa.services;

import org.soulcodeacademy.empresa.domain.Dependente;
import org.soulcodeacademy.empresa.domain.Empregado;
import org.soulcodeacademy.empresa.domain.Projeto;
import org.soulcodeacademy.empresa.domain.dto.DependenteDTO;
import org.soulcodeacademy.empresa.domain.dto.ProjetoDTO;
import org.soulcodeacademy.empresa.repositories.DependenteRepository;
import org.soulcodeacademy.empresa.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private EmpregadoService empregadoService;

    public List<Dependente> listarTodos(){

        return this.dependenteRepository.findAll();
    }

    public Dependente getDependente(Integer idDependente) {
        Optional<Dependente> dependente = this.dependenteRepository.findById(idDependente);

        if (dependente.isEmpty()) {
            throw new RecursoNaoEncontradoError("Dependente não encontrado!");
        } else {
            return dependente.get();
        }

    }

    public Dependente salvar(DependenteDTO dto) {
        Dependente dependente = new Dependente(null, dto.getNome(), dto.getIdade());
        Empregado responsavel = this.empregadoService.getEmpregado(dto.getIdResponsavel());
        dependente.setResponsavel(responsavel);
        Dependente salvo = this.dependenteRepository.save(dependente);
        return salvo;
    }

    public Dependente atualizar(Integer idDependente, @Valid DependenteDTO dto) {
        Dependente dependenteAtual = this.getDependente(idDependente);
        dependenteAtual.setNome(dto.getNome());
        dependenteAtual.setIdade(dto.getIdade());
        Empregado responsavel = this.empregadoService.getEmpregado(dto.getIdResponsavel());
        dependenteAtual.setResponsavel(responsavel);

        Dependente atualizado = this.dependenteRepository.save(dependenteAtual);
        return atualizado;
    }

    public void deletar(Integer idDependente) {
        Dependente dependente = this.getDependente(idDependente);
        this.dependenteRepository.delete(dependente);
    }
}
