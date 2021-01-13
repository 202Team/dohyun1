package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;

public class MemberDAO {

	
	DataSource datafactory;
	

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			datafactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private void closeall(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				
				pstmt.close();
			}
			if (conn != null) {
				
				conn.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}


	public void insert(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member(usernum,id,pw,name,nickname,tell,address) values(?,?,?,?,?,?,?)";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int num = getNum(conn);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getNickname());
			pstmt.setInt(6, dto.getTell());
			pstmt.setString(7, dto.getAddress());
			
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeall(null, pstmt, conn);
		}
		
		
	}


	private int getNum(Connection conn) {
		int num = 0;
	      PreparedStatement pstmt = null;
	      String sql = "SELECT NVL2(MAX(usernum), MAX(usernum)+1, 1) FROM member";
	      ResultSet rs = null;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            num = rs.getInt(1);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         closeall(rs, pstmt, null);
	      }
	      
	      return num;
	   }


	public MemberDTO read(String id) {
		MemberDTO dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *from member where id = ?";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int usernum = rs.getInt("usernum");
				String name=  rs.getString("name");
				String nickname = rs.getString("nickname");
				int tell = rs.getInt("tell");
				String address = rs.getString("address");
				String joined = rs.getString("joined");
				dto = new MemberDTO(usernum, id, null, name, nickname, tell, address, joined);
							
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeall(rs, pstmt, conn);
		}
		
		return dto;
	}


	public void update(MemberDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member set pw=?, nickname=?, tell=?, address=? where = ?";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getNickname());
			pstmt.setInt(3, dto.getTell());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeall(null, pstmt, conn);
		}
		
		
		
	}


	public MemberDTO updateui(String id) {
		
		return read(id);
	}


	public void delete(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where id = ?";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeall(null, pstmt, conn);
		}
		
	}

	public String idcheck(String id) {
		String getid = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *from member where id = ?";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getid = rs.getString("id");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeall(rs, pstmt, conn);
		}
		
		
		
		
		
		return getid;
	}


	public LoginDTO login(LoginDTO loginDTO) {
		LoginDTO login = new LoginDTO(null, null);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *from member where id=? and pw =?";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPw());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
			login = new LoginDTO();
			login.setId(login.getId());
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeall(rs, pstmt, conn);
		}

		return login;
	}
	
	}
	


	
	

