package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SalesDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<AccountTxn> listAccountTxn() {
		String sql = "SELECT * FROM account_txn";

		List<AccountTxn> listAccountTxn = jdbcTemplate.query(sql, 
				BeanPropertyRowMapper.newInstance(AccountTxn.class));

		return listAccountTxn; 
	}
	public List<AccountLogin> listAccountLogin() {
		String sql = "SELECT * FROM account_login";

		List<AccountLogin> listAccountLogin = jdbcTemplate.query(sql, 
				BeanPropertyRowMapper.newInstance(AccountLogin.class));

		return listAccountLogin; 
	}
	
	public void saveAccountTxn(AccountTxn accountTxn) {
		accountTxn.setType("ATM_WITHDRWAL");
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("account_txn").usingColumns("account", "type");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(accountTxn);
		
		insertActor.execute(param);		
	}
	
	public void saveAccountLogin(AccountLogin accountLogin) {
		accountLogin.setChannel("WEB");
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("account_login").usingColumns("account", "channel");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(accountLogin);
		
		insertActor.execute(param);		
	}
	
	public List<Sale> list() {
		String sql = "SELECT * FROM SALES";

		List<Sale> listSale = jdbcTemplate.query(sql, 
				BeanPropertyRowMapper.newInstance(Sale.class));

		return listSale;
	}
	
	public void save(Sale sale) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("SALES").usingColumns("item", "quantity", "amount");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
		
		insertActor.execute(param);		
	}
	
	public Sale get(int id) {
		String sql = "SELECT * FROM SALES WHERE id = ?";
		Object[] args = {id};
		Sale sale = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Sale.class));
		return sale;
	}
	
	public void update(Sale sale) {
		String sql = "UPDATE SALES SET item=:item, quantity=:quantity, amount=:amount WHERE id=:id";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);		
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM SALES WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}
