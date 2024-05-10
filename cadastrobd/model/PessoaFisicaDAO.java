package cadastrobd.model;

import cadastro.model.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoa = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            String sql = "SELECT * FROM pessoa INNER JOIN pessoa_fisica ON pessoa.idPessoa = pessoa_fisica.idPessoa WHERE pessoa.idPessoa = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaFisica();
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(pstmt);
            ConectorBD.close(conn);
        }

        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            String sql = "SELECT * FROM pessoa INNER JOIN pessoa_fisica ON pessoa.idPessoa = pessoa_fisica.idPessoa";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica();
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(pstmt);
            ConectorBD.close(conn);
        }

        return pessoas;
    }

    public boolean incluir(PessoaFisica pessoaFisica) {
        boolean sucesso = false;
        Connection conn = null;
        PreparedStatement pstmtPessoa = null;
        PreparedStatement pstmtPessoaFisica = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            String sqlSeq = "SELECT NEXT VALUE FOR SEQ_idPessoa AS id";
            pstmtPessoa = conn.prepareStatement(sqlSeq);
            rs = pstmtPessoa.executeQuery();
            int idPessoa = 0;
            if (rs.next()) {
                idPessoa = rs.getInt("id");
            }

            String sqlPessoa = "INSERT INTO pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmtPessoa = conn.prepareStatement(sqlPessoa);
            pstmtPessoa.setInt(1, idPessoa);
            pstmtPessoa.setString(2, pessoaFisica.getNome());
            pstmtPessoa.setString(3, pessoaFisica.getLogradouro());
            pstmtPessoa.setString(4, pessoaFisica.getCidade());
            pstmtPessoa.setString(5, pessoaFisica.getEstado());
            pstmtPessoa.setString(6, pessoaFisica.getTelefone());
            pstmtPessoa.setString(7, pessoaFisica.getEmail());
            int linhasAfetadasPessoa = pstmtPessoa.executeUpdate();

            String sqlPessoaFisica = "INSERT INTO pessoa_fisica (idPessoa, cpf) VALUES (?, ?)";
            pstmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
            pstmtPessoaFisica.setInt(1, idPessoa);
            pstmtPessoaFisica.setString(2, pessoaFisica.getCpf());
            int linhasAfetadasPessoaFisica = pstmtPessoaFisica.executeUpdate();

            if (linhasAfetadasPessoa > 0 && linhasAfetadasPessoaFisica > 0) {
                conn.commit();
                sucesso = true;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConectorBD.close(rs);
            ConectorBD.close(pstmtPessoaFisica);
            ConectorBD.close(pstmtPessoa);
            ConectorBD.close(conn);
        }

        return sucesso;
    }

    public boolean alterar(PessoaFisica pessoaFisica) {
        String sql = "UPDATE pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        try (PreparedStatement stmt = ConectorBD.getPrepared(sql)) {
            stmt.setString(1, pessoaFisica.getNome());
            stmt.setString(2, pessoaFisica.getLogradouro());
            stmt.setString(3, pessoaFisica.getCidade());
            stmt.setString(4, pessoaFisica.getEstado());
            stmt.setString(5, pessoaFisica.getTelefone());
            stmt.setString(6, pessoaFisica.getEmail());
            stmt.setInt(7, pessoaFisica.getIdPessoa());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        boolean sucesso = false;
        Connection conn = null;
        PreparedStatement pstmtPessoaFisica = null;
        PreparedStatement pstmtPessoa = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            String sqlPessoaFisica = "DELETE FROM pessoa_fisica WHERE idPessoa=?";
            pstmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
            pstmtPessoaFisica.setInt(1, id);
            int linhasAfetadasPessoaFisica = pstmtPessoaFisica.executeUpdate();

            String sqlPessoa = "DELETE FROM pessoa WHERE idPessoa=?";
            pstmtPessoa = conn.prepareStatement(sqlPessoa);
            pstmtPessoa.setInt(1, id);
            int linhasAfetadasPessoa = pstmtPessoa.executeUpdate();

            if (linhasAfetadasPessoaFisica > 0 && linhasAfetadasPessoa > 0) {
                conn.commit();
                sucesso = true;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConectorBD.close(pstmtPessoaFisica);
            ConectorBD.close(pstmtPessoa);
            ConectorBD.close(conn);
        }

        return sucesso;
    }
}
