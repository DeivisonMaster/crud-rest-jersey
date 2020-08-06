package br.com.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.rest.model.Nota;

public class NotaDAO {

	public Collection<Nota> listarNotas() throws ClassNotFoundException, SQLException {
		Collection<Nota> notas = new ArrayList<Nota>();

		Connection conn = JdbcUtil.getConnection();

		String sql = "SELECT * FROM tb_nota";

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Nota nota = new Nota();

			nota.setId(resultSet.getInt("id"));
			nota.setTitulo(resultSet.getString("titulo"));
			nota.setDescricao(resultSet.getString("descricao"));

			notas.add(nota);
		}

		return notas;
	}
	
	
	public Nota buscarPorId(Integer id) throws SQLException, ClassNotFoundException {
		Connection conn = JdbcUtil.getConnection();

		String sql = "SELECT * FROM tb_nota WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		Nota nota = null;
		
		while (resultSet.next()) {
			nota = new Nota();
			
			nota.setId(resultSet.getInt("id"));
			nota.setTitulo(resultSet.getString("titulo"));
			nota.setTitulo(resultSet.getString("descricao"));
		}
		
		return nota;
	}

	public void adicionaNota(Nota nota) throws SQLException, ClassNotFoundException {
		int idGerado = 0;

		Connection conn = JdbcUtil.getConnection();

		String sql = "INSERT INTO tb_nota(titulo, descricao) VALUES (?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());

		statement.execute();

		statement.close();
		conn.close();
		
	}

	public void editarNota(Nota nota, Integer id) throws SQLException, ClassNotFoundException {
		Connection conn = JdbcUtil.getConnection();

		String sql = "UPDATE tb_nota SET titulo = ?, descricao = ? WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, nota.getId());

		statement.execute();

		statement.close();
		conn.close();

	}

	public void removerNota(Integer id) throws ClassNotFoundException, SQLException {
		Connection conn = JdbcUtil.getConnection();

		String sql = "DELETE FROM tb_nota WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);

		statement.execute();

		statement.close();
		conn.close();
	}

}
