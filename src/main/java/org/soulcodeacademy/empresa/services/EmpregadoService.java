package org.soulcodeacademy.empresa.services;

import org.soulcodeacademy.empresa.domain.Empregado;
import org.soulcodeacademy.empresa.domain.Endereco;
import org.soulcodeacademy.empresa.domain.Projeto;
import org.soulcodeacademy.empresa.domain.dto.EmpregadoDTO;
import org.soulcodeacademy.empresa.repositories.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoService {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ProjetoService projetoService;

    public List<Empregado> listar() {

        return this.empregadoRepository.findAll();
    }

    public List<Empregado> listarFaixaSalarial(Double valor1, Double valor2) {
        return this.empregadoRepository.findBySalarioEntreFaixas(valor1, valor2);
    }

    public Empregado getEmpregado(Integer idEmpregado) {
        Optional<Empregado> empregado = this.empregadoRepository.findById(idEmpregado);

        if (empregado.isEmpty()) {
            throw new RuntimeException("O empregado não foi encontrado!");
        } else {
            return empregado.get();
        }
    }
    public Empregado associarProjeto (Integer idEmpregado, Integer idProjeto) {
        Empregado empregado = this.getEmpregado(idEmpregado);
        Projeto projeto = this.projetoService.getProjeto(idProjeto);
        empregado.getProjetos().add(projeto);
        return this.empregadoRepository.save(empregado);
    }

    public Empregado salvar(EmpregadoDTO dto) {
        Empregado empregado = new Empregado(null, dto.getNome(), dto.getEmail(), dto.getSalario());
        Endereco salvo = this.enderecoService.getEndereco(dto.getIdEndereco());
        empregado.setEndereco(salvo);
        Empregado empregadosalvo = this.empregadoRepository.save(empregado);

        return empregadosalvo;
    }

    public Empregado atualizar(Integer idEmpregado, EmpregadoDTO dto) {
        Empregado empregadoAtual = this.getEmpregado(idEmpregado);

        empregadoAtual.setNome(dto.getNome());
        empregadoAtual.setEmail(dto.getEmail());
        empregadoAtual.setSalario(dto.getSalario());

        Empregado atualizado = this.empregadoRepository.save(empregadoAtual);
        return atualizado;
    }

    public void deletar(Integer idEmpregado) {
        Empregado empregado = this.getEmpregado(idEmpregado);
        this.empregadoRepository.delete(empregado);
    }
}