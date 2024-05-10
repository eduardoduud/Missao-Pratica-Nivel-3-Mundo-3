import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.List;
import java.util.Scanner;

public class CadastroBD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Selecione a opção:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Finalizar");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluir(scanner);
                    break;
                case 2:
                    alterar(scanner);
                    break;
                case 3:
                    excluir(scanner);
                    break;
                case 4:
                    exibirPorId(scanner);
                    break;
                case 5:
                    exibirTodos();
                    break;
                case 0:
                    System.out.println("Finalizando...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void incluir(Scanner scanner) {
        System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            PessoaFisica pessoaFisica = new PessoaFisica();

            System.out.println("Digite o nome:");
            pessoaFisica.setNome(scanner.nextLine());

            System.out.println("Digite o logradouro:");
            pessoaFisica.setLogradouro(scanner.nextLine());

            System.out.println("Digite a cidade:");
            pessoaFisica.setCidade(scanner.nextLine());

            System.out.println("Digite o estado:");
            pessoaFisica.setEstado(scanner.nextLine());

            System.out.println("Digite o telefone:");
            pessoaFisica.setTelefone(scanner.nextLine());

            System.out.println("Digite o email:");
            pessoaFisica.setEmail(scanner.nextLine());

            System.out.println("Digite o CPF:");
            pessoaFisica.setCpf(scanner.nextLine());

            pessoaFisicaDAO.incluir(pessoaFisica);
            System.out.println("Pessoa física incluída com sucesso.");
        } else if (tipo == 2) {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            PessoaJuridica pessoaJuridica = new PessoaJuridica();

            System.out.println("Digite o nome:");
            pessoaJuridica.setNome(scanner.nextLine());

            System.out.println("Digite o logradouro:");
            pessoaJuridica.setLogradouro(scanner.nextLine());

            System.out.println("Digite a cidade:");
            pessoaJuridica.setCidade(scanner.nextLine());

            System.out.println("Digite o estado:");
            pessoaJuridica.setEstado(scanner.nextLine());

            System.out.println("Digite o telefone:");
            pessoaJuridica.setTelefone(scanner.nextLine());

            System.out.println("Digite o email:");
            pessoaJuridica.setEmail(scanner.nextLine());

            System.out.println("Digite o CNPJ:");
            pessoaJuridica.setCnpj(scanner.nextLine());

            pessoaJuridicaDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa jurídica incluída com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar(Scanner scanner) {
        System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

            System.out.println("Digite o ID da pessoa física a ser alterada:");
            int id = scanner.nextInt();
            scanner.nextLine();

            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

            if (pessoaFisica != null) {
                System.out.println("Dados atuais:");
                System.out.println(pessoaFisica);

                System.out.println("Digite o novo nome:");
                pessoaFisica.setNome(scanner.nextLine());

                System.out.println("Digite o novo logradouro:");
                pessoaFisica.setLogradouro(scanner.nextLine());

                System.out.println("Digite a nova cidade:");
                pessoaFisica.setCidade(scanner.nextLine());

                System.out.println("Digite o novo estado:");
                pessoaFisica.setEstado(scanner.nextLine());

                System.out.println("Digite o novo telefone:");
                pessoaFisica.setTelefone(scanner.nextLine());

                System.out.println("Digite o novo email:");
                pessoaFisica.setEmail(scanner.nextLine());

                pessoaFisicaDAO.alterar(pessoaFisica);
                System.out.println("Pessoa física alterada com sucesso.");
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

    System.out.println("Digite o ID da pessoa jurídica a ser alterada:");
    int id = scanner.nextInt();
    scanner.nextLine();

    PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

    if (pessoaJuridica != null) {
        System.out.println("Dados atuais:");
        System.out.println(pessoaJuridica);

        System.out.println("Digite o novo nome:");
        pessoaJuridica.setNome(scanner.nextLine());

        System.out.println("Digite o novo logradouro:");
        pessoaJuridica.setLogradouro(scanner.nextLine());

        System.out.println("Digite a nova cidade:");
        pessoaJuridica.setCidade(scanner.nextLine());

        System.out.println("Digite o novo estado:");
        pessoaJuridica.setEstado(scanner.nextLine());

        System.out.println("Digite o novo telefone:");
        pessoaJuridica.setTelefone(scanner.nextLine());

        System.out.println("Digite o novo email:");
        pessoaJuridica.setEmail(scanner.nextLine());

        pessoaJuridicaDAO.alterar(pessoaJuridica);
        System.out.println("Pessoa jurídica alterada com sucesso.");
    } else {
        System.out.println("Pessoa jurídica não encontrada.");
    }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir(Scanner scanner) {
    System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
    int tipo = scanner.nextInt();
    scanner.nextLine();

    if (tipo == 1) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

        System.out.println("Digite o ID da pessoa física a ser excluída:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

        if (pessoaFisica != null) {
            pessoaFisicaDAO.excluir(id);
            System.out.println("Pessoa física excluída com sucesso.");
        } else {
            System.out.println("Pessoa física não encontrada.");
        }
    } else if (tipo == 2) {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        System.out.println("Digite o ID da pessoa jurídica a ser excluída:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

        if (pessoaJuridica != null) {
            pessoaJuridicaDAO.excluir(id);
            System.out.println("Pessoa jurídica excluída com sucesso.");
        } else {
            System.out.println("Pessoa jurídica não encontrada.");
        }
    } else {
        System.out.println("Tipo inválido.");
    }
}


    private static void exibirPorId(Scanner scanner) {
    System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
    int tipo = scanner.nextInt();
    scanner.nextLine();

    if (tipo == 1) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

        System.out.println("Digite o ID da pessoa física a ser exibida:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

        if (pessoaFisica != null) {
            System.out.println("Pessoa física encontrada:");
            imprimirPessoa(pessoaFisica);
        } else {
            System.out.println("Pessoa física não encontrada.");
        }
    } else if (tipo == 2) {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        System.out.println("Digite o ID da pessoa jurídica a ser exibida:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

        if (pessoaJuridica != null) {
            System.out.println("Pessoa jurídica encontrada:");
            imprimirPessoa(pessoaJuridica);
        } else {
            System.out.println("Pessoa jurídica não encontrada.");
        }
    } else {
        System.out.println("Tipo inválido.");
    }
}

    private static void exibirTodos() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
    int tipo = scanner.nextInt();
    scanner.nextLine();

    if (tipo == 1) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
        if (!pessoasFisicas.isEmpty()) {
            System.out.println("Pessoas físicas encontradas:");
            for (PessoaFisica pf : pessoasFisicas) {
            imprimirPessoa(pf);
            }
        } else {
            System.out.println("Nenhuma pessoa física encontrada.");
        }
    } else if (tipo == 2) {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
        if (!pessoasJuridicas.isEmpty()) {
            System.out.println("Pessoas juridicas:");
            for (PessoaJuridica pj : pessoasJuridicas) {
                imprimirPessoa(pj);
            }
        } else {
            System.out.println("Nenhuma pessoa jurídica encontrada.");
        }
    } else {
        System.out.println("Tipo inválido.");
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
