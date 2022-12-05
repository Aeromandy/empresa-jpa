package org.soulcodeacademy.empresa.services;


import org.soulcodeacademy.empresa.domain.Endereco;
import org.soulcodeacademy.empresa.domain.dto.EnderecoDTO;
import org.soulcodeacademy.empresa.repositories.EnderecoRepository;
import org.soulcodeacademy.empresa.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {


    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listar(){

        return this.enderecoRepository.findAll();
    }

    public Endereco getEndereco(Integer idEndereco) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(idEndereco);

        if (endereco.isEmpty()) {
            throw new RecursoNaoEncontradoError("Endereço não encontrado!");
        } else {
            return endereco.get();
        }
    }

    public Endereco salvar(EnderecoDTO dto) {
        Endereco endereco = new Endereco(null, dto.getCidade(), dto.getUf());
        Endereco salvo = this.enderecoRepository.save(endereco);

        return salvo;
    }

    public Endereco atualizar( Integer idEndereco, EnderecoDTO dto) {
        Endereco enderecoAtual = this.getEndereco(idEndereco);
        enderecoAtual.setCidade(dto.getCidade());
        enderecoAtual.setUf(dto.getUf());

        Endereco atualizado = this.enderecoRepository.save(enderecoAtual);
        return atualizado;

    }

    public void deletar(Integer idEndereco) {
        Endereco endereco = this.getEndereco(idEndereco);
        this.enderecoRepository.delete(endereco);
    }
}