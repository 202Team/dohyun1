package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.domain.BoardDTO;
import kr.co.domain.PageTo;
import sun.nio.cs.ext.ISCII91;

public class BoardDAO {

	private DataSource datafactory;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			datafactory= (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	  private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
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
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }


	public void insert(BoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String sql = "INSERT INTO board (num, author, title, content, repRoot, repStep, repIndent) VALUES(?,?,?,?,?,?,?)";
		
		try {
			conn = datafactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			int num = getNum(conn);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
		
	}
	
	
	  
	private int getNum(Connection conn) {
	      int num = -1;
	      PreparedStatement pstmt = null;
	      String sql = "SELECT NVL2(MAX(num), MAX(num)+1, 1) FROM board";
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
	         closeAll(rs, pstmt, null);
	      }
	      
	      return num;
	   }
	  
	private void increaseReadcnt(Connection conn, int num) {
	      PreparedStatement pstmt = null;
	      String sql = "UPDATE board SET readcnt = readcnt + 1 WHERE num = ?";
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         pstmt.executeUpdate();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         closeAll(null, pstmt, null);
	      }
	      
	   }


	public List<BoardDTO> list() {
	      List<BoardDTO> list = new ArrayList<BoardDTO>();
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "SELECT num, author, title, "
	            + "to_char(writeday, 'yyyy/mm/dd') writeday, "
	            + "readcnt, repRoot, repStep, repIndent  "
	            + "FROM board "
	            + "ORDER BY repRoot desc, repStep asc";
	      ResultSet rs = null;
	      
	      try {
	         conn = datafactory.getConnection();
	                  
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            int num = rs.getInt("num");
	            String title = rs.getString("title");
	            String author = rs.getString("author");
	            String writeday = rs.getString("writeday");
	            int readcnt = rs.getInt("readcnt");
	            int repRoot = rs.getInt("repRoot");
	            int repStep = rs.getInt("repStep");
	            int repIndent = rs.getInt("repIndent");
	            
	            BoardDTO dto = new BoardDTO(num, author, title, null, writeday, readcnt, repRoot, repStep, repIndent);
	            list.add(dto);
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         closeAll(rs, pstmt, conn);
	      }
	      
	      return list;
	   }
	
	
	public BoardDTO read(int num) {
		BoardDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *from board where num = ?";
		boolean ok = false;
		
		try {
			conn = datafactory.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			increaseReadcnt(conn, num);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			     String author = rs.getString("author");
		         String title = rs.getString("title");
		         String content = rs.getString("content");
		         String writeday = rs.getString("writeday");
		         int readcnt = rs.getInt("readcnt");
		            
		         dto= new BoardDTO(num, author, title, content, writeday, readcnt, 0, 0, 0);
			}
			
			ok = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (ok) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			closeAll(rs, pstmt, conn);
		}
		
		return dto;
	}


	 public PageTo page(int curPage) {
		   PageTo to = new PageTo(curPage);
		      List<BoardDTO> list = new ArrayList<BoardDTO>();
		      
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      String sql = "SELECT * FROM (SELECT ROWNUM rnum, num, title, author, writeday, readcnt, repIndent from"
		      		+ "(SELECT * FROM board order by repRoot desc, repStep asc))"
		      		+ " WHERE rnum >= ? AND rnum <= ?";
		      ResultSet rs = null;
		      
		      try {
		         conn = datafactory.getConnection();
		         
		         int amount = getAmount(conn);
		         to.setAmount(amount);
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, to.getStartNum());
		         pstmt.setInt(2, to.getEndNum());
		               
		         rs = pstmt.executeQuery();
		         while (rs.next()) {
		            int num = rs.getInt("num");
		            String title = rs.getString("title");
		            String author = rs.getString("author");
		            String writeday = rs.getString("writeday");
		            int readcnt = rs.getInt("readcnt");
		            int repIndent = rs.getInt("repIndent");
		            
		            BoardDTO dto = new BoardDTO(num, author, title, null, 
		                  writeday, readcnt, -1, -1, repIndent);
		            
		            list.add(dto);
		         }
		         to.setList(list);
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         closeAll(rs, pstmt, conn);
		      }
		      
		      return to;
		   }
	   
	   
	   private int getAmount(Connection conn) {
		 int amount = 0;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select count(num) from board";
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, null);
		}
		 
		 return amount;


	   }
	}