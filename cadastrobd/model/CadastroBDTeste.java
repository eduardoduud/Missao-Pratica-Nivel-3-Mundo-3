package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import java.sql.Connection;
import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) {
        Connection connection = null;
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Joao");
        pessoaFisica.setLogradouro("Blablabla");
        pessoaFisica.setCidade("Belem");
        pessoaFisica.setEstado("PA");
        pessoaFisica.setTelefone("1111-1111");
        pessoaFisica.setEmail("joao@riacho.com");
        pessoaFisica.setCpf("11111111111");
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.incluir(pessoaFisica);

        pessoaFisica.setNome("Joao da Silva");
        pessoaFisicaDAO.alterar(pessoaFisica);

        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
        System.out.println("Pessoas fisicas:");
        for (PessoaFisica pf : pessoasFisicas) {
            imprimirPessoa(pf);
        }

        for (PessoaFisica pf : pessoasFisicas) {
        pessoaFisicaDAO.excluir(pf.getIdPessoa());
        }

        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("JJC");
        pessoaJuridica.setLogradouro("Rua 11, Centro");
        pessoaJuridica.setCidade("Riacho");
        pessoaJuridica.setEstado("PA");
        pessoaJuridica.setTelefone("1212-1212");
        pessoaJuridica.setEmail("jjc@riacho.com");
        pessoaJuridica.setCnpj("11111111111111");
        pessoaJuridicaDAO.incluir(pessoaJuridica);

        pessoaJuridica.setCidade("Riacho do Sul");
        pessoaJuridicaDAO.alterar(pessoaJuridica);

        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
        System.out.println("Pessoas juridicas:");
        for (PessoaJuridica pj : pessoasJuridicas) {
            imprimirPessoa(pj);
        }

        for (PessoaJuridica pj : pessoasJuridicas) {
            pessoaJuridicaDAO.excluir(pj.getIdPessoa());
        }
    }

    private static void imprimirPessoa(PessoaFisica pessoa) {
        System.out.println("Id: " + pessoa.getIdPessoa());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Logradouro: " + pessoa.getLogradouro());
        System.out.println("Cidade: " + pessoa.getCidade());
        System.out.println("Estado: " + pessoa.getEstado());
        System.out.println("Telefone: " + pessoa.getTelefone());
        System.out.println("E-mail: " + pessoa.getEmail());
        System.out.println("CPF: " + pessoa.getCpf());
        System.out.println();
    }

    private static void imprimirPessoa(PessoaJuridica pessoa) {
        System.out.println("Id: " + pessoa.getIdPessoa());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Logradouro: " + pessoa.getLogradouro());
        System.out.println("Cidade: " + pessoa.getCidade());
        System.out.println("Estado: " + pessoa.getEstado());
        System.out.println("Telefone: " + pessoa.getTelefone());
        System.out.println("E-mail: " + pessoa.getEmail());
        System.out.println("CNPJ: " + pessoa.getCnpj());
        System.out.println();
    }
}