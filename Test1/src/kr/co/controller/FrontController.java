package kr.co.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.BoadMoveCommand;
import kr.co.command.BoardDeleteCommand;
import kr.co.command.BoardInsertCommand;
import kr.co.command.BoardInsertUICommand;
import kr.co.command.BoardReadCommand;
import kr.co.command.BoardUpdateCommand;
import kr.co.command.BoardUpdateUICommand;
import kr.co.command.Command;
import kr.co.command.DeleteCommand;
import kr.co.command.InsertCommand;
import kr.co.command.InsertUICommand;
import kr.co.command.LoginCommand;
import kr.co.command.LoginUICommand;
import kr.co.command.LogoutCommand;
import kr.co.command.ReadCommand;
import kr.co.command.UpdateCommand;
import kr.co.command.UpdateUICommand;
import kr.co.domain.CommandAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String sp = uri.substring(cp.length());
		
		Command com = null;
		
		if (sp.equalsIgnoreCase("/insertui.do")) {
			com = new InsertUICommand();
		}else if(sp.equalsIgnoreCase("/insert.do")) {
			com = new InsertCommand();
		}else if (sp.equalsIgnoreCase("/read.do")) {
			com = new ReadCommand();
		}else if (sp.equalsIgnoreCase("/updateui.do")) {
			com = new UpdateUICommand();
		}else if (sp.equalsIgnoreCase("/update.do")) {
			com = new UpdateCommand();
		}else if (sp.equalsIgnoreCase("/delete.do")) {
			com = new DeleteCommand();
		}else if (sp.equalsIgnoreCase("/loginui.do")) {
			com = new LoginUICommand();
		}else if (sp.equalsIgnoreCase("/login.do")) {
			com = new LoginCommand();
		}else if (sp.equalsIgnoreCase("/logout.do")) {
			com = new LogoutCommand();
		}else if (sp.equalsIgnoreCase("/boardinsert.do")) {
			com = new BoardInsertCommand();
		}else if (sp.equalsIgnoreCase("/boardinsertui.do")) {
			com = new BoardInsertUICommand();
		}else if (sp.equalsIgnoreCase("/boardmove.do")) {
			com = new BoadMoveCommand();
		}else if (sp.equalsIgnoreCase("/boardread.do")) {
			com = new BoardReadCommand();
		}else if (sp.equalsIgnoreCase("/boarddelete.do")) {
			com = new BoardDeleteCommand();
		}else if (sp.equalsIgnoreCase("/boardupdate.do")) {
			com = new BoardUpdateCommand();
		}else if (sp.equalsIgnoreCase("/boardupdateui.do")) {
			com = new BoardUpdateUICommand();
		}
		
		
		
		
		
		if (com != null) {
			CommandAction action = com.execute(request, response);
			
			if (action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			}else {
				RequestDispatcher dis = request.getRequestDispatcher(action.getWhere());
				dis.forward(request, response);
			}
		}else {
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("해당 서비스는 제공하지 않습니다.");
			out.print("</body>");
			out.print("</html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
