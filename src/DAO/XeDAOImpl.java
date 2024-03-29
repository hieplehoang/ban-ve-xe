package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectionPool;
import model.Ghe;
import model.Xe;

public class XeDAOImpl implements XeDAO	{

	@Override
	public Xe getXe(long idXe) {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql1 = "SELECT biensoxe,loaighe,soghe FROM xe WHERE idxe = ?";
		Xe xe = null;
		PreparedStatement pre = null;
		ResultSet res;
		try {
			pre = con.prepareStatement(sql1);
			pre.setLong(1, idXe);
			res = pre.executeQuery();
			while (res.next()) {
				xe = new Xe(idXe, res.getString("biensoxe"), res.getString("loaighe"), res.getInt("soghe"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().closePre(pre);
			ConnectionPool.getInstance().freeConnection(con);
		}
		return xe;
	}

}
