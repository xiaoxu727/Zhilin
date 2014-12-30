package DataBase;

import java.sql.*;

import org.stringtree.json.JSONValidatingWriter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class DataBaseManager {

	Statement stmt = null;
	DataAccessorFactory da = null;

	public DataBaseManager() {
		da = new DataAccessorFactory();
		// this.stmt = da.GetStatement();
	}
	/**
	 * 执行sq语句
	 * @param sqlStr
	 */
	public void executeSQL(String sqlStr) {
		try {
			stmt.execute(sqlStr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt = da.getStatement();
				da.closeStatement();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("finally")
	/**
	 * 获取查询结果为json格式的字符串
	 * @param sqlStr
	 * @return
	 */
	public String getJSonResult(String sqlStr) {

		String result = null;
		try {
			QueryRunner qr = new QueryRunner();
			JSONValidatingWriter jw = new JSONValidatingWriter();

			result = jw.write(qr.query(da.getConn(), sqlStr,
					new MapListHandler()));
			da.closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

}
