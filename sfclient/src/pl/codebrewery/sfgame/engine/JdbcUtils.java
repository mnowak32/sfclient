package pl.codebrewery.sfgame.engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcUtils {
	private DataSource ds;

	public JdbcUtils(DataSource ds) {
		this.ds = ds;
	}
	
	public boolean executeQuery(String sql, SqlParam<?> ... params) {
		try (
			Connection conn = ds.getConnection();
			PreparedStatement query = prepareStatement(conn, sql, params);
		) {
			return query.execute();
		} catch (SQLException e) {
			System.err.println("Error during execution of query: '" + sql + "'");
			e.printStackTrace();
		}
		return false;
	}

	public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, SqlParam<?> ... params) {
		try (
			Connection conn = ds.getConnection();
			PreparedStatement query = prepareStatement(conn, sql, params);
			ResultSet rs = query.executeQuery();
		) {
			List<T> $ = new LinkedList<T>();
			while (rs.next()) {
				$.add(rowMapper.map(rs));
			}
		} catch (SQLException e) {
			System.err.println("Error during execution of query: '" + sql + "'");
			e.printStackTrace();
		}
		return null;
	}
	
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, SqlParam<?> ... params) {
		try (
			Connection conn = ds.getConnection();
			PreparedStatement query = prepareStatement(conn, sql, params);
			ResultSet rs = query.executeQuery();
		) {
			if (rs.next()) {
				return rowMapper.map(rs);
			}
		} catch (SQLException e) {
			System.err.println("Error during execution of query: '" + sql + "'");
			e.printStackTrace();
		}
		return null;
	}
	
	private PreparedStatement prepareStatement(Connection conn, String sql, SqlParam<?> ... params) throws SQLException {
		PreparedStatement q = conn.prepareStatement(sql);
		if (params != null) {
			for(int i = 0; i < params.length; i++) {
				params[i].addParam(i + 1, q);
			}
		}
		return q;
	}
	
	@FunctionalInterface
	public interface RowMapper<T> {
		T map(ResultSet rs) throws SQLException;
	}
	
	public static abstract class SqlParam<T> {
		protected T value;
		public SqlParam(T v) {
			value = v;
		}
		abstract void addParam(int i, PreparedStatement query) throws SQLException;
	}
	
	public static class StringParam extends SqlParam<String> {
		public StringParam(String v) {
			super(v);
		}

		@Override
		public void addParam(int i, PreparedStatement query) throws SQLException {
			query.setString(i, value);
		}
	}
	
	public static class LongParam extends SqlParam<Long> {
		public LongParam(long v) {
			super(v);
		}

		@Override
		public void addParam(int i, PreparedStatement query) throws SQLException {
			query.setLong(i, value);
		}
	}
	
	public static class IntParam extends SqlParam<Integer> {
		public IntParam(int v) {
			super(v);
		}
		
		@Override
		public void addParam(int i, PreparedStatement query) throws SQLException {
			query.setInt(i, value);
		}
	}

	public static class LocalDateTimeParam extends SqlParam<LocalDateTime> {
		private static LocalDateTimeConverter converter = new LocalDateTimeConverter(); 
		public LocalDateTimeParam(LocalDateTime v) {
			super(v);
		}
		
		@Override
		public void addParam(int i, PreparedStatement query) throws SQLException {
			query.setTimestamp(i, converter.convertToDatabaseColumn(value));
		}
	}
	
	public static class LocalDateTimeConverter {
		public Timestamp convertToDatabaseColumn(LocalDateTime dt) {
			return dt == null ? null : Timestamp.valueOf(dt);
		}

		public LocalDateTime convertToEntityAttribute(Timestamp ts) {
			return ts == null ? null : ts.toLocalDateTime();
		}
	}

}