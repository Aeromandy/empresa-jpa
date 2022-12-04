package org.soulcodeacademy.empresa.controllers;

import org.soulcodeacademy.empresa.domain.Empregado;
import org.soulcodeacademy.empresa.domain.dto.EmpregadoDTO;
import org.soulcodeacademy.empresa.domain.dto.ProjetoDTO;
import org.soulcodeacademy.empresa.services.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmpregadoController {

     @Autowired
     private EmpregadoService empregadoService;

     @GetMapping("/empregados")
     public List<Empregado> listar() {

          return this.empregadoService.listar();
     }

     @GetMapping("/empregados/salario")
     public List<Empregado> listarFaixaSalarial(@RequestParam Double valor1, @RequestParam Double valor2) {

          return this.empregadoService.listarFaixaSalarial(valor1, valor2);
     }

     @GetMapping("/empregados/{idEmpregado}")
     public Empregado getEmpregado(@PathVariable Integer idEmpregado) {
          return this.empregadoService.getEmpregado(idEmpregado);
     }
     // Verificar se há necessidade desta função existir
     @PostMapping("/empregados")
     public Empregado salvar(@Valid @RequestBody EmpregadoDTO dto) {
          return this.empregadoService.salvar(dto);
     }

     @PostMapping("/empregados/{idEmpregado}/projetos/{idProjeto}")
     public Empregado associarProjeto (@PathVariable Integer idEmpregado, @PathVariable Integer idProjeto) {
          return this.empregadoService.associarProjeto(idEmpregado, idProjeto);
     }

     @PutMapping("/empregados/{idEmpregado}")
     public Empregado atualizar(@PathVariable Integer idEmpregado, @Valid @RequestBody EmpregadoDTO dto) {
          return this.empregadoService.atualizar(idEmpregado, dto);
     }

     @DeleteMapping("/empregados/{idEmpregado}")
     public void deletar(@PathVariable Integer idEmpregado){
     }

}