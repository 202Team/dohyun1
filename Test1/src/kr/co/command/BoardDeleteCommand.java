package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.CommandAction;

public class BoardDeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String snum = request.getParameter("num");
		int num = Integer.parseInt(snum);
		
		new BoardDAO().delete(num);
		
		return new CommandAction(true, "boardmove.do");
	}

}
