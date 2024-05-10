package cadastro.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static int getValue(String SEQ_idPessoa) {
        int nextValue = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectorBD.getConnection();
            String sql = "SELECT NEXT VALUE FOR " + SEQ_idPessoa + " AS next_value";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nextValue = rs.getInt("next_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(pstmt);
            ConectorBD.close(conn);
        }
        
        return nextValue;
    }
}
