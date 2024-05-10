package cadastrobd.model;

import cadastro.model.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoaJuridica = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            String sql = "SELECT * FROM pessoa INNER JOIN pessoa_juridica ON pessoa.idPessoa = pessoa_juridica.idPessoa WHERE pessoa.idPessoa = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setIdPessoa(rs.getInt("idPessoa"));
                pessoaJuridica.setNome(rs.getString("nome"));
                pessoaJuridica.setLogradouro(rs.getString("logradouro"));
                pessoaJuridica.setCidade(rs.getString("cidade"));
                pessoaJuridica.setEstado(rs.getString("estado"));
                pessoaJuridica.setTelefone(rs.getString("telefone"));
                pessoaJuridica.setEmail(rs.getString("email"));
                pessoaJuridica.setCnpj(rs.getString("cnpj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(pstmt);
            ConectorBD.close(conn);
        }

        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            String sql = "SELECT * FROM pessoa INNER JOIN pessoa_juridica ON pessoa.idPessoa = pessoa_juridica.idPessoa";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setIdPessoa(rs.getInt("idPessoa"));
                pessoaJuridica.setNome(rs.getString("nome"));
                pessoaJuridica.setLogradouro(rs.getString("logradouro"));
                pessoaJuridica.setCidade(rs.getString("cidade"));
                pessoaJuridica.setEstado(rs.getString("estado"));
                pessoaJuridica.setTelefone(rs.getString("telefone"));
                pessoaJuridica.setEmail(rs.getString("email"));
                pessoaJuridica.setCnpj(rs.getString("cnpj"));
                pessoasJuridicas.add(pessoaJuridica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(pstmt);
            ConectorBD.close(conn);
        }

        return pessoasJuridicas;
    }

    public boolean incluir(PessoaJuridica pessoaJuridica) {
        boolean sucesso = false;
        Connection conn = null;
        PreparedStatement pstmtPessoa = null;
        PreparedStatement pstmtPessoaJuridica = null;
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
            pstmtPessoa.setString(2, pessoaJuridica.getNome());
            pstmtPessoa.setString(3, pessoaJuridica.getLogradouro());
            pstmtPessoa.setString(4, pessoaJuridica.getCidade());
            pstmtPessoa.setString(5, pessoaJuridica.getEstado());
            pstmtPessoa.setString(6, pessoaJuridica.getTelefone());
            pstmtPessoa.setString(7, pessoaJuridica.getEmail());
            int linhasAfetadasPessoa = pstmtPessoa.executeUpdate();

            String sqlPessoaJuridica = "INSERT INTO pessoa_juridica (idPessoa, cnpj) VALUES (?, ?)";
            pstmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
            pstmtPessoaJuridica.setInt(1, idPessoa);
            pstmtPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
            int linhasAfetadasPessoaJuridica = pstmtPessoaJuridica.executeUpdate();

            if (linhasAfetadasPessoa > 0 && linhasAfetadasPessoaJuridica > 0) {
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
            ConectorBD.close(pstmtPessoaJuridica);
            ConectorBD.close(pstmtPessoa);
            ConectorBD.close(conn);
        }

        return sucesso;
    }

    public boolean alterar(PessoaJuridica pessoaJuridica) {
        boolean sucesso = false;
        Connection conn = null;
        PreparedStatement pstmtPessoa = null;
        PreparedStatement pstmtPessoaJuridica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            String sqlPessoa = "UPDATE pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE idPessoa=?";
            pstmtPessoa = conn.prepareStatement(sqlPessoa);
            pstmtPessoa.setString(1, pessoaJuridica.getNome());
            pstmtPessoa.setString(2, pessoaJuridica.getLogradouro());
            pstmtPessoa.setString(3, pessoaJuridica.getCidade());
            pstmtPessoa.setString(4, pessoaJuridica.getEstado());
            pstmtPessoa.setString(5, pessoaJuridica.getTelefone());
            pstmtPessoa.setString(6, pessoaJuridica.getEmail());
            pstmtPessoa.setInt(7, pessoaJuridica.getIdPessoa());
            int linhasAfetadasPessoa = pstmtPessoa.executeUpdate();

            String sqlPessoaJuridica = "UPDATE pessoa_juridica SET cnpj=? WHERE idPessoa=?";
            pstmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
            pstmtPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            pstmtPessoaJuridica.setInt(2, pessoaJuridica.getIdPessoa());
            int linhasAfetadasPessoaJuridica = pstmtPessoaJuridica.executeUpdate();

            if (linhasAfetadasPessoa > 0 && linhasAfetadasPessoaJuridica > 0) {
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
            ConectorBD.close(pstmtPessoaJuridica);
            ConectorBD.close(pstmtPessoa);
            ConectorBD.close(conn);
        }

        return sucesso;
    }

    public boolean excluir(int id) {
        boolean sucesso = false;
        Connection conn = null;
        PreparedStatement pstmtPessoaJuridica = null;
        PreparedStatement pstmtPessoa = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            String sqlPessoaJuridica = "DELETE FROM pessoa_juridica WHERE idPessoa=?";
            pstmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
            pstmtPessoaJuridica.setInt(1, id);
            int linhasAfetadasPessoaJuridica = pstmtPessoaJuridica.executeUpdate();

            String sqlPessoa = "DELETE FROM pessoa WHERE idPessoa=?";
            pstmtPessoa = conn.prepareStatement(sqlPessoa);
            pstmtPessoa.setInt(1, id);
            int linhasAfetadasPessoa = pstmtPessoa.executeUpdate();

            if (linhasAfetadasPessoaJuridica > 0 && linhasAfetadasPessoa > 0) {
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
            ConectorBD.close(pstmtPessoaJuridica);
            ConectorBD.close(pstmtPessoa);
            ConectorBD.close(conn);
        }

        return sucesso;
    }
}
